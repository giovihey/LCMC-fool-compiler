# 2 Bug Fix: Field Overriding in Class Inheritance

## Problem Description

When a subclass overrode a field from its parent class, the compiler was incorrectly counting the total number of fields. This caused a type checking error during object instantiation.

### Example Error
```
Type checking error in a declaration: Wrong number of arguments for class MyBankLoan (expected 2, got 1) at line 24
```

### Root Cause

In `SymbolTableASTVisitor.java`, when processing field overrides in the `visitNode(ClassNode n)` method, the code was **adding** the overridden field to the field list instead of **replacing** the parent's field.

```java
// BEFORE (incorrect):
if (superEntry == null) {
    fieldEntry = new STentry(nestingLevel, field.getType(), fieldOffset--);
} else {
    // Override case
    fieldEntry = new STentry(nestingLevel, field.getType(), superEntry.offset);
}
fieldsAndMethods.add(field.id);
field.offset = fieldEntry.offset;
virtualTable.put(field.id, fieldEntry);
fieldTypeList.add(-fieldEntry.offset - 1, field.getType());  // ← ALWAYS ADDS
```

This caused subclasses to accumulate fields:
- `BankLoan` has 1 field: `loan: Account`
- `MyBankLoan extends BankLoan` with overridden `loan: TradingAcc` ended up with 2 fields total
- Constructor expected 2 arguments instead of 1

## Solution

Differentiate between adding a new field and overriding an existing field:
- **New fields**: use `fieldTypeList.add()`
- **Overridden fields**: use `fieldTypeList.set()` to replace at the same position

### Code Fix

In `SymbolTableASTVisitor.java`, replace the field handling loop (around lines 245-270):

```java
// AFTER (correct):
for (FieldNode field : n.fields) {
    if (print) printNode(field);

    if (fieldsAndMethods.contains(field.id)) {
        System.out.println("Field or Method " + field.id + " at line " + field.getLine() + " already declared ");
        stErrors++;
    } else {
        STentry superEntry = virtualTable.get(field.id);
        STentry fieldEntry;

        if (superEntry == null) {
            // New field - create new entry and ADD to list
            fieldEntry = new STentry(nestingLevel, field.getType(), fieldOffset--);
            fieldsAndMethods.add(field.id);
            field.offset = fieldEntry.offset;
            virtualTable.put(field.id, fieldEntry);
            fieldTypeList.add(-fieldEntry.offset - 1, field.getType());
        } else {
            if (superEntry.type instanceof ArrowTypeNode) {
                System.out.println("Can't override method with field, line: " + field.getLine());
                stErrors++;
            } else {
                // Override existing field - REPLACE in list at same position
                fieldEntry = new STentry(nestingLevel, field.getType(), superEntry.offset);
                fieldsAndMethods.add(field.id);
                field.offset = fieldEntry.offset;
                virtualTable.put(field.id, fieldEntry);
                fieldTypeList.set(-fieldEntry.offset - 1, field.getType());  // ← SET instead of ADD
            }
        }
    }
}
```

## Result

After the fix:
- ✅ 0 type checking errors
- ✅ Correct field count for classes with inheritance
- ✅ Object instantiation works correctly with proper argument count

### Test Case
```fool
class BankLoan (loan: Account) { ... }
class MyBankLoan extends BankLoan (loan: TradingAcc) { ... }

var bl:BankLoan = new MyBankLoan(new TradingAcc(50000,40000));  // Now works!
```

The constructor now correctly expects 1 argument (the overridden `loan: TradingAcc`) instead of 2.
