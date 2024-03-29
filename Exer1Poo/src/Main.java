import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String res;
        int divisor,dividendo,precisao;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello and welcome!");

        System.out.println("Digite um valor inteiro para ser o seu dividendo: ");
        dividendo = in.nextInt();
        System.out.println("Digite um valor inteiro para ser o seu divisor: ");
        divisor = in.nextInt();
        System.out.println("Digite um valor inteiro para a precisão que deseja: ");
        precisao = in.nextInt();

        res = Matematica.recebeValores(dividendo,divisor,precisao);
        if(res == null){
            System.out.println(res);
            System.out.println("Não foi possível realizar a divisão");
        }else{
            System.out.println(res);
        }
    }
}