import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
		
		// Go through the tests i.e. elections
        for(int t = 0; t < T; t++) {
            int n = sc.nextInt();

			// Count votes
            int[] candidateVotes = new int[n];
            for(int i = 0; i < n; i++) {
                candidateVotes[i] = sc.nextInt();
            }

			// Get the maximal vote counts for and the candidate(s)
            int[] maxResults = max(candidateVotes);
            int maxVote = maxResults[0];
            int maxIndex = maxResults[1];
            int nrDuplicateMax = maxResults[2];
           
			// If there are more than 1 max votes => no winner 
            if(nrDuplicateMax > 0) {
                System.out.println("no winner");
            } else {
                int totalVotes = sum(candidateVotes);
                if(((double) maxVote / totalVotes) > 0.5) {
                    System.out.println("majority winner " + (maxIndex + 1));  // +1 for correct output format
                } else {
                    System.out.println("minority winner " + (maxIndex + 1));
                }
            }
        }
        sc.close();
    }

    /**
     * Returns the max-value, max-index and the number of duplicates 
     * for the input list. 
     * If the list empty, it will return [Integer.MIN_VALUE, -1, -1].
     */
    static public int[] max(int[] list) {
        int n = list.length;

        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        int nrDuplicateMax = -1;

        for(int i = 0; i < n; i++) {
            if(list[i] > maxValue) {
                maxValue = list[i];
                maxIndex = i;
                nrDuplicateMax = 0;
            } else if(list[i] == maxValue) {
                nrDuplicateMax++;
            }
        }
        return new int[] { maxValue, maxIndex, nrDuplicateMax };
    }

    /**
     * Returns the sum of the input list.
     */
    static public int sum(int[] list) {
        int n = list.length;
        int total = 0;

        for(int i = 0; i < n; i++) {
            total += list[i];
        }
        return total;
    }
}
