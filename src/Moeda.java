import com.google.gson.annotations.SerializedName;

public class Moeda {
    @SerializedName("base_code")
    private String moedaOrigem;

    @SerializedName("target_code")
    private String moedaDestino;

    @SerializedName("conversion_result")
    private String valorMoedaDeDeDestino;

    private String valorMoedaDeOrigem;






    public Moeda(String moedaOrigem, String moedaDestino, String valorMoedaDeOrigem, String valorMoedaDeDeDestino) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorMoedaDeOrigem = valorMoedaDeOrigem;
        this.valorMoedaDeDeDestino = valorMoedaDeDeDestino;
    }

    public String getMoedaOrigem() {
        return moedaOrigem;
    }

    public String getMoedaDestino() {
        return moedaDestino;
    }

    public String getValorMoedaDeDeDestino() {
        return valorMoedaDeDeDestino;
    }

    public void setValorMoedaDeOrigem(String valorMoedaDeOrigem) {
        this.valorMoedaDeOrigem = valorMoedaDeOrigem;
    }

    public String getValorMoedaDeOrigem() {
        return valorMoedaDeOrigem;
    }

    public Double valorFinal (){
       Double valorFinal =  Double.parseDouble(this.getValorMoedaDeOrigem()) * Double.parseDouble((this.getValorMoedaDeDeDestino()));
       System.out.printf("O valor final é de: %.2f%n" , valorFinal);
        return valorFinal;
    }


    @Override
    public String toString() {
        return "Moeda {" +
                "Moeda de Origem= '" + getMoedaOrigem() + '\'' +
                ", Moeda de Destino= '" + getMoedaDestino() + '\'' +
                ", Valor para Conta= '" + getValorMoedaDeDeDestino() + '\'' +
                ", Valor para Conversão= '" + getValorMoedaDeOrigem() + '\'' +
                ", Valor Final = " + valorFinal();
                };
    }



