public class Hilo {

    public void metodoFor(){
        try {
            for(int i=0; i<10; i++){
                System.out.println("Hola soy: "+i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
