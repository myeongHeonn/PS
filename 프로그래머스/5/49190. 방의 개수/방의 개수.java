import java.util.HashSet;
import java.util.Set;

class Solution {
	static final int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };

	static final long OFFSET = 200_001;
	static final long SIZE = 400_003;

	static long key(long x, long y) {
		return (x + OFFSET) * SIZE + (y + OFFSET);
	}

	public int solution(int[] arrows) {
		Set<Long> nodes = new HashSet<>();
		Set<Long> edges = new HashSet<>();

		long x = 0, y = 0;
		nodes.add(key(x, y));
		int answer = 0;

		for (int dir : arrows) {
			for (int i = 0; i < 2; i++) {
				long nx = x + dx[dir];
				long ny = y + dy[dir];

				long cur = key(x, y);
				long next = key(nx, ny);
				long edge = cur * 8 + dir;

				if (!edges.contains(edge)) {
					if (nodes.contains(next))
						answer++;

					edges.add(edge);
					edges.add(next * 8 + (dir + 4) % 8);
				}

				nodes.add(next);
				x = nx;
				y = ny;
			}
		}
		
		return answer;
	}
}