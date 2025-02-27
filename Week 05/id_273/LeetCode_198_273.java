//198. 打劫家舍

//解法1：动态规划（二维数组版）		执行用时：0ms
//思路：对于当前屋子, 我们可以选择偷也可以选择不偷：
//			如果选择偷的话上一个屋子就一定不能偷
//			如果选择不偷的话上一个屋子可以选择偷, 也可以选择不偷
//		我们可以将每间屋子选择偷与不偷的收益通过二维数组dp存储
//		那么对于第N间屋子, 选择偷的收益：dp[n][1] = nums[n] +  dp[n - 1][0] (0 - 不偷)
//						   选择不偷的收益：dp[n][0] = dp[n - 1][0] or dp[n - 1][1]
//		那么只要比较第N间屋子偷与不偷的收益最大值即可
//时间复杂度：O(n)
//空间复杂度：O(n)
public int rob(int[] nums) {
	if (nums.length == 0 || nums == null) return 0;
	int[][] dp = new int[nums.length][2];
	dp[0][1] = nums[0];
	dp[0][0] = 0;
	for (int i = 1; i < nums.length; i++) {
		dp[i][1] = dp[i - 1][0] + nums[i];
		dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
	}
	return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
}

//解法1.1：动态规划（一维数组版）		执行用时：0ms
//思路：基于解法1, 二维数组dp改为存储第n间屋子偷与不偷的最大收益
//时间复杂度：O(n)
//空间复杂度：O(n)
public int rob(int[] nums) {
	if (nums == null || nums.length == 0) return 0;
	if (nums.length == 1) return nums[0];
	int[] dp = new int[nums.length];
	dp[0] = nums[0];
	dp[1] = Math.max(dp[0], nums[1]);
	for (int i = 2; i < nums.length; i++) {
		dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
	}
	return dp[nums.length - 1];
}

//解法1.2：动态规划（变量暂存）		执行用时：0ms
//思路：基于解法1.2, 我们发现在每次计算最大收益时, 只需要比较当前屋子的最大收益与上一间屋子的最大收益
//		那么就可以设置3个参数, prev代表第n - 2个屋子的最大收益, temp代表第n - 1个屋子的最大收益, curr代表第n个屋子的最大收益
//时间复杂度：O(n)
//空间复杂度：O(1)
public int rob(int[] nums) {
	int prev = 0, curr = 0, temp = 0;
	for (int i : nums) {
		temp = curr;
		curr = Math.max(prev + i, temp);
		prev = temp;
	}
	return curr;
}






