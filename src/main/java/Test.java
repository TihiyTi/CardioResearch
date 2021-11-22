public class Test {
    public static void main(String[] args) {

        int a = 1;
        int b = a*2;

        int[] massive = {1,2,3,4};

        massive[0] = massive[0]*massive[0];
        massive[1] = massive[1]*2;
        massive[2] = massive[2]*2;

        for (int i = 0; i < 4 ; i++) {
            massive[i] = massive[i]*massive[i];
        }

        for (int i = 0; i < 4 ; i++) {
            massive[i] = massive[i]*2;
        }


        int sum = 0;

        for (int i = 0; i < 4; i++) {
            sum = sum + massive[i];
        }


    }
}
