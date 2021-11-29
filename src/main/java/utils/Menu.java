package utils;

import service.AirlinesService;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    public Scanner in = new Scanner(System.in);
    public AirlinesService airlinesService = new AirlinesService();

    public void mainMenu() {

        int op = -1;
        while (op != 0) {
            printLineSeparator();
            //printa as opçoes do menu
            System.out.println("Digite a opção que você deseja:" +
                    "\n1 - Cadastrar Vôo" +
                    "\n2 - Cadastrar Passageiro" +
                    "\n3 - Ver Vôo" + //chama um submenu
                    "\n4 - Ver Passageiro" + //chama um submenu
                    "\n5 - Alterar Vôo" +
                    "\n6 - Alterar Passageiro" +
                    "\n7 - Excluir Vôo" +
                    "\n8 - Excluir Passageiro" +
                    "\n0 - Sair");
            op = in.nextInt();

            switch (op) {
                case 1: //1 - Cadastrar Vôo
                    airlinesService.saveFlight();
                    break;
                case 2: //2 - Cadastrar Passageiro
                    airlinesService.savePassanger();
                    break;
                case 3: //3 - Ver Vôo
                    subMenu(op);
                    break;
                case 4: //4 - Ver Passageiro
                    subMenu(op);
                    break;
                case 5: //5 - Alterar Vôo
                    airlinesService.updateFlight(); //check
                    break;
                case 6: //6 - Alterar Passageiro
                    airlinesService.updatePassanger(); //check
                    break;
                case 7: //7 - Excluir Vôo
                    airlinesService.dropFlight();
                    break;
                case 8: //8 - Excluir Passageiro
                    airlinesService.dropPassanger();
                    break;
                case 0: //0 - Sair
                    break;
                default:
                    System.out.println("Resposta inválida!");
                    break;
            }
        }
    }

    public void printLineSeparator(){
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------");
    }

    public void subMenu(int op) {

        if (op == 3) { //opcao de ver voos
            printLineSeparator();
            System.out.println("Digite a sub opção desejada \n"
                    + "1 - Ver todos os vôos\n" +
                      "2 - Ver os vôos com mais passageiros (assentos ocupados)\n" +
                      "3 - Ver o vôo com menos passageiros\n" +
                      "4 - Ver o vôo mais longo\n" +
                      "5 - Ver o vôo mais curto\n" +
                      "6 - Ver a média de ocupação dos vôos\n"
                    + "0 - Sair");
            int subOp = in.nextInt();
            switch (subOp) {
                case 1: //1 - Ver todos os vôos
                    airlinesService.getAllFlights();
                    break;
                case 2: //2 - Ver os vôos com mais passageiros (assentos ocupados)
                    airlinesService.getMaxOccupiedSeatsFlight();
                    break;
                case 3: //3 - Ver o vôo com menos passageiros
                    airlinesService.getLessPassangersFlight(); //alterar logica
                    break;
                case 4: //4 - Ver o vôo mais longo
                    airlinesService.getMaxDistanceFlight();
                    break;
                case 5: //5 - Ver o vôo mais curto
                    airlinesService.getShortDistanceFlight();
                    break;
                case 6: //6 - Ver a média de ocupação dos vôos
                    //TODO implementar metodo --ted
                    break;
                case 0: //0 - Sair
                    mainMenu();
                    break;
                default:
                    System.out.println("Resposta inválida");
                    break;

            }
        }

        else if(op==4){
            printLineSeparator();
            System.out.println("Digite a sub opção desejada \n"
                    + "1 - Ver todos os passageiros\n" +
                      "2 - Buscar por vôo (exibe todos os passageiros)\n"
                    + "0 - Sair");
            int subOp = in.nextInt();
            switch (subOp) {
                case 1: //1 - Ver todos os passageiros
                    airlinesService.getAllPassangers();
                    break;
                case 2: //2 - buscar por vôo (exibe todos os passageiros)
                    //TODO implementar metodo --ted
                    airlinesService.findAllPassengers();
                    break;
                case 0: //0 - Sair
                    mainMenu();
                    break;
                default: //caso o user tenha dado um valor por leitor que nao seja compativel, ele cai aqui exibindo a mensagem
                    System.out.println("Resposta inválida");
                    break;
            }
        }
    }
}
