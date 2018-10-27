package DP;

public class Floyd_Warshell {
    int[][] dist;
    public void minPath(int[][] g){
        dist = new int[g.length][g.length];
        for (int k = 0; k < g.length; k++){
            for (int i = 0; i < g.length; i++){
                for (int j = 0; j < g.length; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
    }
}
