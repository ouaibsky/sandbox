
//package org.icroco.codingame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Player {
	static long start = System.currentTimeMillis();
	static boolean debug = false;

	public static int[][] extractMatrix(LinkedList<String> aInput, int ySize, int xSize) {
		int[][] result = new int[ySize][xSize];

		for (int y = 0; y < ySize; y++) {
			// String[] cells = aInput.removeFirst().split("\\s+");
			// Integer[] cells = toIntArray(aInput.removeFirst()); // TODO uncomment if
			// char array
			char[] cells = aInput.removeFirst().toCharArray(); // TODO uncomment if char
																// array
			for (int x = 0; x < cells.length; x++) {
				// result[y][x] = Integer.valueOf(cells[x]);
				result[y][x] = cells[x] == '.' ? -1 : 0; // TODO uncomment if char array
				// System.err.println("cell "+result[y][x]);
			}
		}
		return result;
	}

	public static void printMatrix(int[][] matrix, int padding) {
		for (int y = 0; y < matrix.length; y++) {
			StringBuilder b = new StringBuilder();
			for (int x = 0; x < matrix[y].length; x++) {
				b.append(String.format("%1$" + padding + "s", matrix[y][x]));
			}
			debug(b.toString());
		}
	}

	public static void main(String[] argv) throws Exception {
		String line;
		Scanner sc = new Scanner(System.in);
		long start = System.currentTimeMillis();
		LinkedList<String> input = new LinkedList<>();

		int remainingLine = readLen(input, sc);
		for (int i = 0; i < remainingLine; i++) {
			input.add(sc.nextLine());
			debug("Line: " + input.peekLast());

		}

		debug("Compute: " + (System.currentTimeMillis() - start));
		List<String> output = getSolution(input);

		for (String s : output) {
			System.out.println(s);
		}
	}

	private static int readLen(LinkedList<String> input, Scanner sc) {
		input.add(sc.nextLine());
		String height = sc.nextLine();
		input.add(height);
		return Integer.valueOf(height);
	}

	static List<String> getSolution(LinkedList<String> aInput) {
		List<String> output = new ArrayList<>();

		int width = Integer.valueOf(aInput.poll());
		int height = Integer.valueOf(aInput.poll());
		int[][] matrix = extractMatrix(aInput, height, width);
		debug((System.currentTimeMillis() - start) + ", width: " + width + " height: "
				+ height);

		printMatrix(matrix, 3);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				debug("Compute: " + (System.currentTimeMillis() - start));
				if (matrix[y][x] == -1)
					continue;
				String msg = x + " " + y + " ";
				int x2 = x + 1;
				for (; x2 < width; x2++) {
					if (matrix[y][x2] == 0) {
						msg += (x2) + " " + y + " ";
						break;
					}
				}
				if (x2 == width)
					msg += "-1 -1 ";

				int j2 = y + 1;
				for (; j2 < height; j2++) {
					if (matrix[j2][x] == 0) {
						msg += x + " " + (j2) + " ";
						break;
					}
				}
				if (j2 == height)
					msg += "-1 -1 ";
				output.add(msg);
			}
		}

		return output;
	}

	private static final void debug(String str) {
		if (debug) {
			System.err.println(str);
		}
	}

	public static int[] toIntArray(String line) {
		debug("line: " + line);
		String[] SA = line.split("\\s+");
		int[] IA = new int[SA.length];
		for (int i = 0; i < SA.length; i++)
			IA[i] = Integer.valueOf(SA[i]);

		return IA;
	}

}
