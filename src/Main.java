
public class Main{

    public static void main(String []args ) {

        World daisyWorld = new World();

        for(int i=0 ; i< 50 ; i++ ){
            daisyWorld.tick();
        }
    }
}