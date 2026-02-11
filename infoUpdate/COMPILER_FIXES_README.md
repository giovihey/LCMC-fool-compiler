# 1  FOOL Compiler - Bug Fixes Documentation

## Overview
This document details all the bugs that were identified and fixed in the FOOL compiler implementation. The compiler now successfully compiles and executes object-oriented programs with classes, methods, and inheritance.

---

## Test Program
The following test program was used to identify the bugs:

```fool
let
  class A(n:int) {
    fun get:int() n;
  }
  
  var obj:A = new A(666);
in
  print(obj.get()); 
```

**Expected output**: `666`

---

## Bugs Fixed

### 1. SymbolTableASTVisitor.java - ClassCallNode Visitor

**File**: `SymbolTableASTVisitor.java`  
**Method**: `visitNode(ClassCallNode n)`  
**Lines**: ~375-395

#### Problem
The method had two critical issues:
1. When `entry.type` was not a `RefTypeNode`, an error was printed but execution continued, leading to an invalid cast
2. `classTable.get(classRef.classId)` could return `null`, causing a `NullPointerException` when calling `.get(n.methodId)` on it

#### Original Code
```java
@Override
public Void visitNode(ClassCallNode n) {
    if (print) printNode(n);
    STentry entry = stLookup(n.classId);
    if (entry == null) {
        System.out.println(n.classId + " at line: " + n.getLine() + " not declared");
        stErrors++;
    } else {
        if (!(entry.type instanceof RefTypeNode)) {
            System.out.println(n.classId + " at line: " + n.getLine() + " is not a RefTypeNode");
            stErrors++;
        }
        n.entry = entry;
        n.nestingLevel = nestingLevel;

        RefTypeNode classRef = (RefTypeNode) entry.type;
        STentry methodEntry = classTable.get(classRef.classId).get(n.methodId);  // NPE HERE
        // ...
    }
}
```

#### Fixed Code
```java
@Override
public Void visitNode(ClassCallNode n) {
    if (print) printNode(n);
    STentry entry = stLookup(n.classId);
    if (entry == null) {
        System.out.println(n.classId + " at line: " + n.getLine() + " not declared");
        stErrors++;
    } else if (!(entry.type instanceof RefTypeNode)) {  // Changed to else if
        System.out.println(n.classId + " at line: " + n.getLine() + " is not a RefTypeNode");
        stErrors++;
    } else {  // Added else block
        n.entry = entry;
        n.nestingLevel = nestingLevel;

        RefTypeNode classRef = (RefTypeNode) entry.type;
        Map<String, STentry> classVirtualTable = classTable.get(classRef.classId);
        
        if (classVirtualTable == null) {  // Added null check
            System.out.println("Class " + classRef.classId + " at line: " + n.getLine() + " not found in class table");
            stErrors++;
        } else {
            STentry methodEntry = classVirtualTable.get(n.methodId);
            if (methodEntry == null) {
                System.out.println("Method: " + n.methodId + " at line: " + n.getLine() + ", was not declared");
                stErrors++;
            } else {
                n.methodEntry = methodEntry;
            }
        }
    }
    for (Node arg : n.argList) {
        visit(arg);
    }
    return null;
}
```

#### Error Messages
```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.util.Map.get(Object)" 
because the return value of "java.util.Map.get(Object)" is null
	at compiler.SymbolTableASTVisitor.visitNode(SymbolTableASTVisitor.java:389)
```

---

### 2. SymbolTableASTVisitor.java - NewNode Visitor

**File**: `SymbolTableASTVisitor.java`  
**Method**: `visitNode(NewNode n)`  
**Lines**: ~400-415

#### Problem
When a class was not declared, `n.entry` remained `null`, causing a `NullPointerException` in subsequent visitors (specifically `PrintEASTVisitor` when trying to access `n.entry.nl`).

#### Original Code
```java
@Override
public Void visitNode(NewNode n) {
    if (print) printNode(n);
    if(this.classTable.containsKey(n.classId)){
        n.entry = symTable.getFirst().get(n.classId);
    } else {
        System.out.println("Class " + n.classId + " at line: " + n.getLine() + " was not declared");
        stErrors++;
        // n.entry remains null here!
    }
    for (Node arg : n.argList) {
        visit(arg);
    }
    return null;
}
```

#### Fixed Code
```java
@Override
public Void visitNode(NewNode n) {
    if (print) printNode(n);
    
    STentry classEntry = symTable.get(0).get(n.classId);
    
    if (classEntry == null) {
        System.out.println("Class " + n.classId + " at line: " + n.getLine() + " was not declared");
        stErrors++;
        // Create a dummy entry to prevent NullPointerException in subsequent visitors
        n.entry = new STentry(0, new ClassTypeNode(new ArrayList<>(), new ArrayList<>()), 0);
    } else {
        n.entry = classEntry;
    }
    
    for (Node arg : n.argList) {
        visit(arg);
    }
    return null;
}
```

#### Key Changes
1. Changed from `classTable.containsKey()` to `symTable.get(0).get()` for consistency
2. Created a dummy `STentry` with an empty `ClassTypeNode` when the class is not found
3. This allows subsequent visitors to continue without crashing

#### Error Messages
```
Exception in thread "main" java.lang.NullPointerException: Cannot read field "nl" because "n.entry" is null
	at compiler.PrintEASTVisitor.visitNode(PrintEASTVisitor.java:248)
```

---

### 3. TypeCheckEASTVisitor.java - NewNode Visitor

**File**: `TypeCheckEASTVisitor.java`  
**Method**: `visitNode(NewNode n)`  
**Lines**: ~320-330

#### Problem
The method tried to access `fields.get(i)` without first checking if the number of arguments matched the number of fields. When a class was not properly declared (using the dummy entry), the `fields` list was empty, causing an `IndexOutOfBoundsException`.

#### Original Code
```java
@Override
public TypeNode visitNode(NewNode n) throws TypeException {
    if (print) printNode(n, n.classId);
    for (int i = 0; i < n.argList.size(); i++) {
        TypeNode fieldType = ((ClassTypeNode) n.entry.type).fields.get(i);  // IndexOutOfBoundsException!
        TypeNode passedField = visit(n.argList.get(i));
        if (!isSubtype(passedField, fieldType)) {
            throw new TypeException("Wrong field type in class " + n.classId + " at line: ", n.getLine());
        }
    }
    return new RefTypeNode(n.classId);
}
```

#### Fixed Code
```java
@Override
public TypeNode visitNode(NewNode n) throws TypeException {
    if (print) printNode(n, n.classId);
    
    ClassTypeNode classType = (ClassTypeNode) n.entry.type;
    
    // Check if the number of arguments matches the number of fields
    if (classType.fields.size() != n.argList.size()) {
        throw new TypeException("Wrong number of arguments for class " + n.classId + 
                              " (expected " + classType.fields.size() + ", got " + n.argList.size() + ")", 
                              n.getLine());
    }
    
    // Type check each argument
    for (int i = 0; i < n.argList.size(); i++) {
        TypeNode fieldType = classType.fields.get(i);
        TypeNode passedField = visit(n.argList.get(i));
        if (!isSubtype(passedField, fieldType)) {
            throw new TypeException("Wrong field type in class " + n.classId + " at line: ", n.getLine());
        }
    }
    return new RefTypeNode(n.classId);
}
```

#### Error Messages
```
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
	at java.base/java.util.ArrayList.get(ArrayList.java:427)
	at compiler.TypeCheckEASTVisitor.visitNode(TypeCheckEASTVisitor.java:322)
```

---

### 4. ASTGenerationSTVisitor.java - LetInProg Visitor

**File**: `ASTGenerationSTVisitor.java`  
**Method**: `visitLetInProg(LetInProgContext c)`  
**Lines**: ~43-48

#### Problem
The most critical bug: class declarations were not being added to the `declist`. The parser was only visiting `c.dec()` (which includes `vardec` and `fundec`), but not `c.cldec()` (class declarations). This meant that class declarations were completely ignored during symbol table construction.

#### Original Code
```java
@Override
public Node visitLetInProg(LetInProgContext c) {
    if (print) printVarAndProdName(c);
    List<DecNode> declist = new ArrayList<>();
    for (DecContext dec : c.dec()) declist.add((DecNode) visit(dec));  // Only vardec and fundec
    return new ProgLetInNode(declist, visit(c.exp()));
}
```

#### Fixed Code
```java
@Override
public Node visitLetInProg(LetInProgContext c) {
    if (print) printVarAndProdName(c);
    List<DecNode> declist = new ArrayList<>();
    
    // ADD CLASSES FIRST!
    for (CldecContext cl : c.cldec()) {
        declist.add((DecNode) visit(cl));
    }
    
    // THEN ADD OTHER DECLARATIONS (var and fun)
    for (DecContext dec : c.dec()) {
        declist.add((DecNode) visit(dec));
    }
    
    return new ProgLetInNode(declist, visit(c.exp()));
}
```

#### Why Classes Must Come First
Classes must be processed before variable and function declarations because:
1. Variables can have types that reference classes (e.g., `var obj:A`)
2. Functions can have parameters or return types that reference classes
3. The symbol table needs class information available when processing other declarations

#### Error Messages
```
Class A at line: 7 was not declared
Class A at line: 9 not found in class table
You had 2 symbol table errors.
```

#### Debug Output
Before fix:
```
DEBUG: declist size = 1
DEBUG: declist contains: VarNode
```

After fix:
```
DEBUG: declist size = 2
DEBUG: declist contains: ClassNode
DEBUG: declist contains: VarNode
```

---

### 5. CodeGenerationASTVisitor.java - ClassNode Visitor

**File**: `CodeGenerationASTVisitor.java`  
**Method**: `visitNode(ClassNode n)`  
**Lines**: ~348

#### Problem
Missing space in string concatenation when generating `push` instruction. The code generated `pushlabel` instead of `push label`, causing an assembly syntax error.

#### Original Code
```java
String code = "";
for (String label : dispatchTable) {
    code = nlJoin(code,
            "push" + label,  // Missing space!
            "lhp",
            "sw",
            "lhp",
            "push 1",
            "add",
            "shp");
}
```

#### Fixed Code
```java
String code = "";
for (String label : dispatchTable) {
    code = nlJoin(code,
            "push " + label,  // Added space
            "lhp",
            "sw",
            "lhp",
            "push 1",
            "add",
            "shp");
}
```

#### Error Messages
```
Assembling generated code.
You had: 0 lexical errors and 1 syntax errors.
line 5:0 missing ':' at 'lhp'
```

#### Explanation
The assembler was seeing `pushlhp` as an unknown instruction, then encountering `lhp` and expecting it to be a label (requiring a colon). The fix ensures proper spacing between the instruction and its operand.

---

## Additional Bugs Found (Minor)

### Typo in SymbolTableASTVisitor.java
**Line**: ~248

```java
// Wrong
System.out.println("Can't ovverride method with field, line: " + field.getLine());

// Correct
System.out.println("Can't override method with field, line: " + field.getLine());
```

**Issue**: "ovverride" should be "override" (three 'v's instead of two)

---

## Testing Results

### Before Fixes
```
Generating ST via lexer and parser.
You had 0 lexical errors and 0 syntax errors.
Generating AST.
Enriching AST via symbol table.
Class A at line: 7 was not declared
Exception in thread "main" java.lang.NullPointerException
```

### After Fixes
```
Generating ST via lexer and parser.
You had 0 lexical errors and 0 syntax errors.
Generating AST.
Enriching AST via symbol table.
You had 0 symbol table errors.
Visualizing Enriched AST.
[... complete AST ...]
Checking Types.
Type of main program expression is: IntType
You had 0 type checking errors.
You had a total of 0 front-end errors.
Generating code.
Assembling generated code.
You had: 0 lexical errors and 0 syntax errors.
Running generated code via Stack Virtual Machine.
666
Process finished with exit code 0
```

---

## Summary

| File | Method | Issue Type | Impact |
|------|--------|-----------|--------|
| SymbolTableASTVisitor.java | visitNode(ClassCallNode) | Logic error | NullPointerException |
| SymbolTableASTVisitor.java | visitNode(NewNode) | Missing null safety | NullPointerException |
| TypeCheckEASTVisitor.java | visitNode(NewNode) | Missing bounds check | IndexOutOfBoundsException |
| ASTGenerationSTVisitor.java | visitLetInProg | Missing functionality | Classes not processed |
| CodeGenerationASTVisitor.java | visitNode(ClassNode) | String formatting | Invalid assembly |

**Total Bugs Fixed**: 5 critical bugs + 1 typo

All bugs have been resolved and the compiler now successfully compiles and executes object-oriented FOOL programs.

---

## Key Takeaways

1. **Defensive Programming**: Always check for `null` before dereferencing, especially when dealing with symbol tables and type information
2. **Error Recovery**: When errors are detected, create valid dummy objects to allow compilation to continue and find additional errors
3. **Parser Completeness**: Ensure all language constructs are properly collected and added to the AST
4. **String Formatting**: Be careful with string concatenation in code generation, especially with assembly instructions
5. **Ordering Matters**: In declaration lists, classes must be processed before other declarations that might reference them

---

*Document created: February 2026*  
*Compiler: FOOL (Functional Object-Oriented Language)*  
*Target: Stack Virtual Machine (SVM)*
