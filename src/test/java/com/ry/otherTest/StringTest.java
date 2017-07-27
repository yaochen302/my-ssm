package com.ry.otherTest;

import java.util.regex.Pattern;

public class StringTest {
	public static void main(String[] args) {
		String str1 = "a,b,c,,,,";
		String str2 = "a,,b,c";
		String[] s1 = str1.split(",");
		String[] s2 = str2.split(",");
		System.out.println(str1.split(","));
		System.out.println(str2.split(","));

		String[] s3 = { "q", "w", "" };
		System.out.println(s3);

		System.out.println("123456".substring(0, 2));

		System.out.println("悦家云".contains("东原") ? "东原悦家云" : "保利悦家云");
		System.out.println(a());

		Object o1 = true ? new Integer(1) : new Double(2.0);
		Object o2;

		if (true)
			o2 = new Integer(1);
		else
			o2 = new Double(2.0);

		System.out.println(o1);
		System.out.println(o2);

		Integer i = new Integer(1);
		if (i.equals(1))
			i = null;
		Double d = new Double(2.0);
		Object o = true ? i : d; // NullPointerException!
		System.out.println(o);

	}

	static int[][] a() {
		return new int[0][];
	}

	int[] b()[] {
		return new int[0][];
	}

	int c()[][] {
		return new int[0][];
	}

	int[][] a = { {} };
	int[] b[] = { {} };
	int c[][] = { {} };
}

class OperatorDemo {
	public static void main(String[] args) {
		int i = 1;
		int k = 1;
		int j = (i = 2) * i; // expression 1
		i = 1;
		i += (i = 2) * i; // expression 2
		j = j + (j = 2); // expression 3
		k += (k = 4) * (k + 2); // expression 4
		System.out.printf("i=%d,j=%d,k=%d", i, j, k);
		for (int b = 0; b < 10000; b++) {

			for (int a = 0; a < 10; a++) {
				System.out.println((Integer) a);
			}
		}
	}
}
