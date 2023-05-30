/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TarikAlrayan.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
* @file Project2
* @description spell checker project  
* @assignment 2 
* @date 9/04/2023
* @author TARIK ALRAYAN tarik.alrayan@stu.fsm.edu.tr
*/

//a class to handel the dictionary operations and the LevenshteinDistance algorathim
public class TarikAlrayanDictionary<T extends Comparable<T>> {

    // this method takes a file and read the words inside it the but them into a binary tree
    public void getDicFromFile(File f, TarikAlrayanBinaryTree<T> tree) {
        try {
            Scanner scanner = new Scanner(f, "UTF-8");
            while (scanner.hasNext()) {
                String word = scanner.next();
                tree.insert((T) word);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    //this method takes the root of the binary tree of dictionary a word and a k suggestions number to find the closest words to the given word from the dictonary and return a new tree contains the suggestions 
    public TarikAlrayanBinaryTree<T> findClosestWords(TarikAlrayanNode<T> root, T targetWord, int k) {

        //an array to hold the distance for the suggestions word 
        Object[][] closestWords = new Object[k][2];
        findClosestWords(root, targetWord, closestWords, k);

        TarikAlrayanBinaryTree<T> result = new TarikAlrayanBinaryTree<>();
        for (int i = 0; i < closestWords.length && closestWords[i][0] != null; i++) {
            result.insert((T) closestWords[i][0]);
        }

        return result;
    }

    //this method use the levenshteinDistace to compare the distace and find the closest word 
    private void findClosestWords(TarikAlrayanNode<T> node, T targetWord, Object[][] closestWords, int k) {
        if (node == null || contains(node, targetWord)) {
            return;
        }

        int distance = getLevenshteinDistance(node.getData().toString(), targetWord.toString());

        for (int i = 0; i < closestWords.length; i++) {
            if (closestWords[i][0] == null) {
                closestWords[i][0] = node.getData();
                closestWords[i][1] = (distance);
                break;
            } else {
                int currDistance = (Integer) closestWords[i][1];

                //then loop the 2d array and store the information like this [distance][word] and check if the array got a distance bigger then the last distance it should replace with it to have an 2d array with the closest word in the first index 
                if (distance < currDistance) {
                    for (int j = closestWords.length - 1; j > i; j--) {
                        closestWords[j][0] = closestWords[j - 1][0];
                        closestWords[j][1] = closestWords[j - 1][1];
                    }
                    closestWords[i][0] = node.getData();
                    closestWords[i][1] = (distance);
                    break;
                }
            }
        }

        findClosestWords(node.getLeft(), targetWord, closestWords, k);
        findClosestWords(node.getRight(), targetWord, closestWords, k);
    }

    //this method implement the LevenshteinDistance algorithim and return the distance bettwen the givven srtings 
    private int getLevenshteinDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
            }
        }

        return dp[m][n];
    }

    //check if the given value is exsist in the givven binary tree root
    public boolean contains(TarikAlrayanNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.getData().equals(value)) {
            return true;
        }

        return contains(node.getLeft(), value) || contains(node.getRight(), value);
    }
}
