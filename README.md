# Divide and Conquer Algorithms

# 📌 Overview
This project implements several classic divide-and-conquer algorithms in Java.  
The goals are:
- Practice safe recursion patterns.
- Analyze running-time recurrences (Master Theorem, Akra–Bazzi intuition).
- Collect and evaluate metrics (time, depth, comparisons, allocations).
- Present results with clean Git history and structured workflow.

Implemented algorithms:
1. **MergeSort** – recursive sorting with linear merge.
2. **QuickSort** – randomized pivot, bounded recursion depth.
3. **Deterministic Select (Median-of-Medians)** – linear-time selection.
4. **Closest Pair of Points (2D)** – O(n log n) divide-and-conquer.

---

# ⚙️ Architecture Notes
- **Recursion depth** is controlled:
  - QuickSort always recurses into the smaller partition, iterates on the larger → depth bounded by `O(log n)` with high probability.
  - MergeSort uses balanced splits → depth ≈ `log₂ n`.
  - Deterministic Select recurses only into one side (the smaller).
  - Closest Pair recursively splits by x-coordinate; strip is handled in O(n).
- **Allocations** are reduced:
  - MergeSort reuses a single buffer array.
  - QuickSort is in-place.
  - Select is fully in-place.
- **Cut-off strategy**:
  - For MergeSort, arrays of size ≤ 16 are sorted via insertion sort (reduces constant factors).
- **Metrics collected**:
  - Execution time (ms)
  - Recursion depth
  - Number of comparisons
  - Number of allocations
  - Results exported as CSV for plotting.

---

## 📊 Recurrence Analysis

### 1. MergeSort
- Recurrence:  
  `T(n) = 2T(n/2) + Θ(n)`  
- Master Theorem (Case 2):  
  `a = 2, b = 2, f(n) = Θ(n)` → `n^(log_b a) = n`  
  ⇒ `T(n) = Θ(n log n)`

### 2. QuickSort
- Average case recurrence (random pivot):  
  `T(n) = T(αn) + T((1-α)n) + Θ(n)` with expected split α ≈ 0.5  
- Expected depth: `Θ(log n)`  
- Expected time: `Θ(n log n)`  
- Worst case: `Θ(n²)` (rare due to random pivot + small-first recursion).

### 3. Deterministic Select (Median-of-Medians)
- Recurrence:  
  `T(n) = T(n/5) + T(7n/10) + Θ(n)`  
- Solves to: `T(n) = Θ(n)`  
- Depth: `Θ(log n)` but with smaller constants than full recursion.

# 4. Closest Pair of Points
- Recurrence:  
  `T(n) = 2T(n/2) + Θ(n)` (split + merge step with strip check)  
- Master Theorem (Case 2):  
  ⇒ `T(n) = Θ(n log n)`

---

# 📈 Results (example)
Plots were generated using collected metrics (time, depth, comparisons) for input sizes up to 1e6.

- MergeSort and QuickSort both achieve `O(n log n)` scaling.
- QuickSort shows slightly better constants for random data, but MergeSort is more stable.
- Deterministic Select consistently outperforms sorting-based selection (`Arrays.sort + get(k)`).
- Closest Pair implementation matches the quadratic brute-force on small n and outperforms it drastically on large n.



---

# ✅ Summary
- Theory and practice mostly align:
  - MergeSort: balanced, stable, predictable `n log n`.
  - QuickSort: faster in practice, depth bounded by design.
  - Select: true linear-time, matches expected results.
  - Closest Pair: `n log n` verified, strip optimization works.
- Constant factors (cache, memory allocations, JVM GC) play a noticeable role at smaller input sizes.
- Workflow follows proper Git branching and commit storyline.

---

