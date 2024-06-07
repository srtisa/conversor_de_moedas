import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        List <Moeda> historicoDeBusca = new ArrayList<>();

        //configurando json
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        //leitura da moeda
        Scanner scanner = new Scanner(System.in);
        int buscaDoNumeroDoMenu;

        //loop do menu
        do {
            //menu

            System.out.println("**************************************************\n" +
                    "Qual moeda você quer converter?\n" +
                    "Euro em Real digite 1.\n" +
                    "Dólar em Real digite 2.\n" +
                    "Won em Real digite 3.\n" +
                    "Peso Argentino em Real digite 4.\n" +
                    "Dólar Australiano em Real digite 5.\n" +
                    "Peso colombiano em Real digite 6 \n" +
                    "Para parar o programa digite 0. ===> ");

            buscaDoNumeroDoMenu = scanner.nextInt(); //busca o numero
            if (buscaDoNumeroDoMenu < 1 || buscaDoNumeroDoMenu > 7 ) {
                System.out.println("Entrada inválida.");
                if (buscaDoNumeroDoMenu == 0 ) {
                    System.out.println("Obrigada por usar meu programa!");
                    if (historicoDeBusca.size() > 0) {
                        System.out.println(historicoDeBusca);
                    }
                    break;
                }
            } else {
                String entradaParaBusca = String.valueOf(buscaDoNumeroDoMenu);


                String valorParaConverter = "";
                String valorASerConvertido = "BRL";

                System.out.println("Agora o valor da troca: ");
                double valorMonetarioParaTroca = scanner.nextDouble();

                //selecionando a troca
                if (entradaParaBusca.equalsIgnoreCase("1")) {
                    valorParaConverter = "EUR";
                } else if (entradaParaBusca.equalsIgnoreCase("2")) {
                    valorParaConverter = "USD";
                } else if (entradaParaBusca.equalsIgnoreCase("3")) {
                    valorParaConverter = "KRW";
                } else if (entradaParaBusca.equalsIgnoreCase("4")) {
                    valorParaConverter = "ARS";
                } else if (entradaParaBusca.equalsIgnoreCase("5")) {
                    valorParaConverter = "AUD";
                } else if (entradaParaBusca.equalsIgnoreCase("6")) {
                    valorParaConverter = "COP";

                }
                System.out.println("Você escolheu: " + valorParaConverter);


                //busca
                try {
                    String endereco = ("https://v6.exchangerate-api.com/v6/ba109ab78da5f96fb26379f6/pair/" + valorParaConverter + "/" + valorASerConvertido);

                    java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(endereco))
                            .build();

                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    String jsonDaApi = response.body();
                   //System.out.println(response.body());

                    //calculo

                    // Acessar o valor de "conversion_result"
                    String jsonResponse = response.body();
                    JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                    gson.fromJson(jsonResponse, Moeda.class);
                    String moedaDestino = String.valueOf(jsonObject.get("conversion_rate").getAsString());


                    Moeda moedaDoCliente = new Moeda(valorParaConverter, valorASerConvertido, String.valueOf(valorMonetarioParaTroca), moedaDestino);

                    //testes
                    System.out.println("Moeda que o cliente escolheu: " + moedaDoCliente.getMoedaOrigem());
                    System.out.println("Moeda que vai ser convertida: " + moedaDoCliente.getMoedaDestino());
                    System.out.println("Valor a ser convertido para outra moeda: " + moedaDoCliente.getValorMoedaDeDeDestino());
                    System.out.println("Valor no Json da moeda para fazer a conta: " + moedaDoCliente.getValorMoedaDeOrigem());
                    System.out.println(moedaDoCliente);
                    // adciona a array
                    historicoDeBusca.add(moedaDoCliente);

                    System.out.println("Deseja continuar? Digite 7 se sim e 0 para não. ===>");
                    entradaParaBusca = String.valueOf(scanner.nextInt());
                    if (entradaParaBusca.equalsIgnoreCase(String.valueOf(0))){
                        System.out.println("Obrigada por usar o programa.");
                        if (historicoDeBusca.size() > 0) {
                            System.out.println(historicoDeBusca);
                        }
                        break;

                    }


                } catch (Exception e) {
                    System.out.println("Houve um erro." + e.getMessage());
                }


            }

        }
                while (true);
            }

}
