package algos;

import java.util.*;



public class seriesPemutations {
    static Set<String> perm = new HashSet<String>(); // avoid dup
    static Scanner sc = new Scanner(System.in);

    private static String initString(int n) {
        char[] arr = new char[n];
        for (int i = 0; i < arr.length/2; i++) {
            arr[i] = 'A';
        }
        for (int i = arr.length/2; i < arr.length; i++) {
            arr[i] = 'B';
        }
        return String.valueOf(arr);
    }
    
    // l is left, r is right
    public static void permute(String str, int l, int r) { // other variations include working substring and answer as params
        if (l == r) {
            int count = 0;
            
            for (int i = 0; i < str.length() - 1; i++) { // check for instances of B in N-1 games
                if (str.charAt(i) == 'B') count++;
            }
            if(count == str.length()/2+1) return; 
            
            perm.add(str);

        } else {
            for (int i = l; i <= r; i++) { // backtracking
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public static String swap(String str, int i, int j) {
        char temp; 
        char[] arr = str.toCharArray(); // convert to arr so avoid creating multiple new strings in memory
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return String.valueOf(arr); 
    }
    
    public static void main(String[] args) {
        System.out.println("How many games?");
        int n = Integer.parseInt(sc.nextLine());
        String str = initString(n);
        System.out.println(str);
        
        permute(str, 0, n - 1);
        System.out.println("Ways one team can win " + perm.size()); // instances when team A wins
        System.out.println("Total ways of winning " + perm.size() * 2); // total ways
    }
}
