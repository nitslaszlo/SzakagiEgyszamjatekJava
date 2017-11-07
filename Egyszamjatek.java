package egyszamjatek;

import java.awt.Label;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Egyszamjatek {

	public static void main(String[] args) {

		new Egyszamjatek();
	}

	public Egyszamjatek() {

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Egyszamjatek - 2017.10.20");
		f.setVisible(true);

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setVisible(true);
		f.add(p);

		p.add(new Label("1. feladat: Adatok beolvasasa"));

		ArrayList<Jatekos> j = new ArrayList<Jatekos>();

		try {
			List<String> sorok = Files.readAllLines(Paths.get("egyszamjatek.txt"));
			for (int i = 0; i < sorok.size(); i++) {
				j.add(new Jatekos(sorok.get(i).split(" ")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f, e.getMessage());
		}

		p.add(new Label("3. feladat: Jatekosok szama: " + j.size()));

		p.add(new Label("4. feladat: Fordulok szama: " + j.get(0).FordulokSzama()));

		// 5. feladat:
		boolean vanEgyesTipp = false;
		for (Jatekos i : j) {
			if (i.TippFordulo(1) == 1) {
				vanEgyesTipp = true;
				break;
			}
		}
		p.add(new Label("5. feladat: Az elso forduloban " + (vanEgyesTipp ? "" : "nem") + "volt egyes tipp!"));

		//6. feladat:
		int legnagyobbTipp = j.get(0).getLegnagyobbTipp();
		for (int i = 1; i < j.size(); i++) {
			if (j.get(i).getLegnagyobbTipp() > legnagyobbTipp) {
				legnagyobbTipp = j.get(i).getLegnagyobbTipp();
			}
		}
		p.add(new Label("6. feladat: A legnagyobb tipp a fordulok soran: " + legnagyobbTipp));

		f.pack();
		String sFordulo = JOptionPane
				.showInputDialog("7. feladat: Kerem a fordulo sorszamat [1-" + j.get(0).FordulokSzama() + "]");
		int iFordulo = Integer.parseInt(sFordulo);
		if (iFordulo < 1 || iFordulo > j.get(0).FordulokSzama())
			iFordulo = 1;

		// 8. feladat:
		int[] stat = new int[100];
		for (Jatekos i : j) {
			stat[i.TippFordulo(iFordulo)]++;
		}
		int nyertesTipp = -1;
		for (int i = 1; i < stat.length; i++) {
			if (stat[i] == 1) {
				nyertesTipp = i;
				break;
			}
		}
		if (nyertesTipp != -1) {
			p.add(new Label("8. feladat: A nyertes tipp a megadott forduloban: " + nyertesTipp));
		} else {
			p.add(new Label("8. feladat: Nem volt egyedi tipp a megadott forduloban."));
		}

		String nyertesNeve = "";
		if (nyertesTipp != -1) {
			for (Jatekos i : j) {
				if (i.TippFordulo(iFordulo) == nyertesTipp) {
					nyertesNeve = i.getNev();
					break;
				}
			}
			p.add(new Label("9. feladat: A megadott fordulo nyertese: " + nyertesNeve));
		} else {
			p.add(new Label("9. feladat: Nem volt nyertes a megadott forduloban!"));
		}

		// 10. feladat:
		if (nyertesTipp != -1) {
			p.add(new Label("10. feladat: nyertes.txt irasa"));
			ArrayList<String> ki = new ArrayList<String>();
			ki.add("Fordulo sorszama: " + iFordulo);
			ki.add("Nyertes tipp: " + nyertesTipp);
			ki.add("Nyertes jatekos: " + nyertesNeve);
			try {
				Files.write(Paths.get("nyertes.txt"), ki);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(f, e.getMessage());
			}
		}
		f.pack();
	}
}