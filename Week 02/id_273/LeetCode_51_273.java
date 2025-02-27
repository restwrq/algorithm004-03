//51. N皇后


//解法1：回溯		执行用时击败约43%
//思路：由于皇后的攻击范围是所在的行/列/对角线 ,因此在某一行放置了一个皇后, 该行不可能再放置第二个皇后
//    	因此我们只需要对行数作记录, 判断每一列的位置是否available即可
//		通过3个Set分别存放当前皇后所占据的列,左/右对角线, 下一个皇后在放置之前通过Set判断当前位置是否可以放置
class {
	private Set<Integer> col = new HashSet<>();
	private Set<Integer> diag1 = new HashSet<>();
	private Set<Integer> diag2 = new HashSet<>();

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		dfs(result, new ArrayList<String>(), n, 0);
		return result;
	}

	private void dfs(List<List<String>> result, List<String> list, int n, int row) {
		if (row == n) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (col.contains(i) || diag1.contains(row + i) || diag2.contains(row - i)) continue;
			char[] temp = new char[n];
			Arrays.fill(temp, '.');
			temp[i] = 'Q';
			list.add(new String(temp));
			col.add(i);
			diag1.add(row + i);
			diag2.add(row - i);
			//drill down
			dfs(result, list, n, row + 1);
			//reverse
			list.remove(list.size() - 1);
			col.remove(i);
			diag1.remove(row + i);
			diag2.remove(row - i);
		}
	}
}