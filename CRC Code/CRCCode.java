import java.util.Scanner;

class CRC {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the size of the data:");
        int dataSize = scan.nextInt();
        int data[] = new int[dataSize];
        System.out.println("Enter the data bits (0 or 1):");
        for (int i = 0; i < dataSize; i++) {
            System.out.print("Enter bit number " + (i + 1) + ": ");
            data[i] = scan.nextInt();
        }

        System.out.println("Enter the size of the divisor:");
        int divisorSize = scan.nextInt();
        int divisor[] = new int[divisorSize];
        System.out.println("Enter the divisor bits (0 or 1):");
        for (int i = 0; i < divisorSize; i++) {
            System.out.print("Enter bit number " + (i + 1) + ": ");
            divisor[i] = scan.nextInt();
        }

        int[] remainder = divide(data, divisor);

        System.out.println("\nThe CRC code generated is:");
        for (int bit : data) {
            System.out.print(bit);
        }
        for (int bit : remainder) {
            System.out.print(bit);
        }
        System.out.println();

        int[] sentData = new int[data.length + remainder.length];
        System.arraycopy(data, 0, sentData, 0, data.length);
        System.arraycopy(remainder, 0, sentData, data.length, remainder.length);

        System.out.println("Simulated sent data:");
        for (int bit : sentData) {
            System.out.print(bit);
        }
        System.out.println();

        receive(sentData, divisor);
        scan.close();
    }

    static int[] divide(int[] oldData, int[] divisor) {
        int[] data = new int[oldData.length + divisor.length - 1];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        for (int i = 0; i < oldData.length; i++) {
            if (data[i] == 1) {
                for (int j = 0; j < divisor.length; j++) {
                    data[i + j] ^= divisor[j];
                }
            }
        }
        int[] remainder = new int[divisor.length - 1];
        System.arraycopy(data, data.length - divisor.length + 1, remainder, 0, divisor.length - 1);
        return remainder;
    }

    static void receive(int[] data, int[] divisor) {
        int[] remainder = divide(data, divisor);
        boolean hasError = false;
        for (int bit : remainder) {
            if (bit != 0) {
                hasError = true;
                break;
            }
        }
        if (hasError) {
            System.out.println("There is an error in the received data.");
        } else {
            System.out.println("Data was received without any error.");
        }
    }
}
