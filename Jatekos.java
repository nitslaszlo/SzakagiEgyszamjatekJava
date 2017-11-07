package egyszamjatek;

import java.util.Arrays;

public class Jatekos {
	private String Nev;
	private int[] Tippek;

	public Jatekos(String[] m) {
		this.Nev = m[0];
		Tippek = new int[m.length - 1];
		for (int i = 1; i < m.length; i++) {
			this.Tippek[i - 1] = Integer.parseInt(m[i]);
		}
	}

	public String getNev() {
		return this.Nev;
	}

	public int[] getTippek() {
		return this.Tippek;
	}

	public int FordulokSzama() {
		return this.Tippek.length;
	}

	public int getLegnagyobbTipp() {
		int[] seged = this.Tippek.clone();
		Arrays.sort(seged);
		return seged[seged.length - 1];
	}

	public int TippFordulo(int fordulo) {
		return this.Tippek[fordulo - 1];
	}
}
