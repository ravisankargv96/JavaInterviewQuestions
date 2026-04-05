These notes provide a comprehensive guide to String Rotation in Java, as discussed in the Code Decode video.

---

### **1. Introduction to String Rotation**
String rotation involves moving characters from one end of a string to the other, as if the string were a circular loop.

*   **Left Rotation:** Characters are moved from the beginning (left) of the string to the end.
    *   *Example (String "DECODE", rotate by 2):* "DE" moves to the end $\rightarrow$ **"CODEDE"**.
*   **Right Rotation:** Characters are moved from the end (right) of the string to the beginning.
    *   *Example (String "DECODE", rotate by 2):* "DE" moves to the front $\rightarrow$ **"DEDECO"**.

---

### **2. Interview Question 1: Check if a String is a Rotation**
**Problem:** Given two strings, determine if one is a rotation of the other.

#### **The Logic: The Concatenation Trick**
To check if `String B` is a rotation of `String A`:
1.  Check if both strings have the same length. (If lengths differ, they cannot be rotations).
2.  Concatenate the original string with itself: `String concatenated = A + A;`
3.  Check if `B` is a substring of `concatenated`.
    *   If `concatenated.contains(B)` is true, then B is a rotation.

#### **Example Walkthrough:**
*   **Original String:** "DECODE"
*   **Concatenated String:** "DECODE" + "DECODE" = **"DECODEDECODE"**
*   **Target (Left Rotated 2):** "CODEDE" $\rightarrow$ Found in "DE**CODEDE**CODE" (True).
*   **Target (Right Rotated 2):** "DEDECO" $\rightarrow$ Found in "DECO**DEDECO**DE" (True).
*   **Target (Swapped):** "DEOCDE" $\rightarrow$ Not found in the concatenated string (False).

---

### **3. Interview Question 2: Implement Left Rotation**
**Problem:** Write a program to rotate a string to the left by $n$ characters.

#### **The Logic:**
If you want to rotate by `r` characters:
1.  The string is split at index `r`.
2.  The first part (index 0 to $r-1$) moves to the back.
3.  The second part (index $r$ to the end) moves to the front.

#### **Java Implementation:**
Using the `substring(int beginIndex, int endIndex)` method (Note: `beginIndex` is inclusive, `endIndex` is exclusive).

```java
public String leftRotate(String str, int r) {
    // Part 1: From index r to the end
    String part1 = str.substring(r); 
    
    // Part 2: From index 0 to r (r is excluded)
    String part2 = str.substring(0, r); 
    
    return part1 + part2;
}
```
*Input: "DECODE", r=2 $\rightarrow$ "CODE" + "DE" = "CODEDE"*

---

### **4. Interview Question 3: Implement Right Rotation**
**Problem:** Write a program to rotate a string to the right by $n$ characters.

#### **The Logic:**
Right rotation is slightly more complex because the "break point" is calculated from the end.
1.  **Calculate Partition (P):** `Length of String - r`.
2.  The string is split at index `P`.
3.  The part from `P` to the end moves to the front.
4.  The part from index 0 to `P` moves to the back.

#### **Java Implementation:**
```java
public String rightRotate(String str, int r) {
    int partition = str.length() - r;
    
    // Part 1: From partition to the end (moves to front)
    String part1 = str.substring(partition);
    
    // Part 2: From 0 to partition (moves to back)
    String part2 = str.substring(0, partition);
    
    return part1 + part2;
}
```
*Input: "DECODE", r=2 $\rightarrow$ Partition is 4 ($6-2$).*
*Result: "DE" (from index 4,5) + "DECO" (from index 0-3) = "DEDECO"*

---

### **5. Key Java Concepts Used**
*   **Immutability:** String objects in Java are immutable. When we concatenate or use `substring()`, the original string remains unchanged, and a new string is created in memory.
*   **Substring Method:**
    *   `substring(index)`: Returns a substring starting from the specified index to the end.
    *   `substring(start, end)`: Returns a substring from the `start` index up to, but not including, the `end` index.
*   **Contains Method:** The `contains()` method checks if a specific sequence of characters exists within a string.

### **6. Important Edge Case**
If you rotate a string by exactly half its length (e.g., rotating "CODE" by 2), the **Left Rotation** and **Right Rotation** will yield the same result ("DECO").