import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Project1 {
	public static void main(String[] args) throws IOException {
		FactoryImpl fac = new FactoryImpl();
		fac.doClear();
		File file = new File(args[0]);
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		File output = new File(args[1]);
		try (FileWriter fil = new FileWriter(output)) {



			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(" ");
				if (tokens[0].equals("P")) {
					fil.write(fac.print() + "\n");
					continue;
				}

				if (tokens[0].equals("AF")) {
					Product product = new Product(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					fac.addFirst(product);
					continue;

				}

				if (tokens[0].equals("AL")) {
					Product product = new Product(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					fac.addLast(product);
					continue;

				}
				if (tokens[0].equals("A")) {
					Integer index = Integer.parseInt(tokens[1]);
					Product product = new Product(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

					try {
						fac.add(index, product);
					} catch (Exception e) {
						fil.write("Index out of bounds." + "\n");


					}

					continue;

				}
				if (tokens[0].equals("RF")) {

					try {
						fil.write(fac.removeFirst().toString()+"\n");
					} catch (Exception e) {
						fil.write("Factory is empty." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("RL")) {
					try {
						fil.write(fac.removeLast().toString()+"\n");
					} catch (Exception e) {
						fil.write("Factory is empty." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("RI")) {
					Integer index = Integer.parseInt(tokens[1]);

					try {
						fil.write(fac.removeIndex(index).toString()+"\n");
					} catch (Exception e) {
						fil.write("Index out of bounds." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("RP")) {
					Integer value = Integer.parseInt(tokens[1]);
					try {
						fil.write(fac.removeProduct(value).toString()+"\n");

					} catch (Exception e) {
						fil.write("Product not found." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("F")) {
					Product product = new Product(Integer.parseInt(tokens[1]), null);
					try {
						fil.write(fac.find(product.getId()).toString()+"\n");

					} catch (Exception e) {
						fil.write("Product not found." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("G")) {
					Integer index = Integer.parseInt(tokens[1]);

					try {
						fil.write(fac.get(index).toString()+"\n");
					} catch (Exception e) {
						fil.write("Index out of bounds." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("U")) {
					Product product = new Product(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					try {
						fil.write(fac.update(product.getId(), product.getValue()).toString()+"\n");
					} catch (Exception e) {
						fil.write("Product not found." + "\n");
					}
					continue;

				}
				if (tokens[0].equals("FD")) {
					fil.write(fac.filterDuplicates()+"\n");
					continue;

				}
				if (tokens[0].equals("R")) {
					fac.reverse();
					fil.write(fac.print() + "\n");

					continue;

				}


			}
		}


	}
}