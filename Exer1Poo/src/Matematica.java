/*
Na classe Matematica deve haver um método de classe (static) que recebe 3 parâmetros:
dividendo (inteiro), divisor (inteiro), precisao (inteiro). Este método retorna uma
String com o resultado da divisão do dividendo pelo divisor, com tantas casas decimais
significativas quanto a precisão. Se a divisão não for possível, retorna null.
 */
import java.text.DecimalFormat;

public class Matematica {
    public static String recebeValores(int dividendo,int divisor,int precisao){
        double res ;
        String resString ;
        DecimalFormat df = new DecimalFormat("#." + new String(new char[precisao]).replace('\0', '#'));

        if(divisor == 0){
            return null;
        }else{
            res = (double)dividendo/divisor;
            resString = df.format(res);
            return resString;
        }
    }
}
