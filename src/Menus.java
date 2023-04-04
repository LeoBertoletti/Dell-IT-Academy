import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
    ArrayList<String> historico = new ArrayList<>();
    DecimalFormat formatoDinheiro = new DecimalFormat("###,###.00");
    Dados dados = Dados.getInstance();
    Scanner seletor = new Scanner(System.in);

    public void menuPrincipal() throws InterruptedException, FileNotFoundException {
        int nSelecionado;
        Scanner seletor = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV("data.csv");
        Dados dados = Dados.getInstance();
        dados.setDados(leitor.getCsvData());

        while (true) {
            System.out.println("\n\n\n\n\n--------------------------------------------------");
            System.out.println("Bem vindo ao Sistema da Transportadora Dely\n\n\n\n\n");
            System.out.println("Selecione:");
            System.out.println("0 - Encerrar o sistema");
            System.out.println("1 - Consulta de trechos x modalidade");
            System.out.println("2 - Cadastro de transporte");
            System.out.println("3 - Dados históricos");
            seletor = new Scanner(System.in);
            if (seletor.hasNextInt()) {
                nSelecionado = seletor.nextInt();
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                Thread.sleep(1500);
                continue;
            }
            switch (nSelecionado) {
                case 0:
                    System.exit(0);
                    seletor.close();
                    break;
                case 1:
                    consultaTrecho();
                    break;
                case 2:
                    cadastroMercadorias(cadastroRotas());
                    break;
                case 3:
                    historico();
                    break;
                default:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                    Thread.sleep(1500);
                    menuPrincipal();
                    break;
            }
        }

    }

    private void consultaTrecho() throws InterruptedException {
        ArrayList<Integer> rota = new ArrayList<Integer>();
        Dados dados = Dados.getInstance();
        int distanciaTrecho;
        boolean segundoLoop = false;

        // consulta trecho para calculo de orçamento
        // bem parecido com o cadastroRotas, mas limitado em duas cidades
        consultatrecho: while (true) {
            int nSelecionado;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSelecione a " + (rota.size() + 1) + "ª cidade:");
            dados.printCidades();
            System.out.println("\n\t\t0 - Encerrar sistema");
            System.out.println("\t\t25 - Voltar ao menu");
            seletor = new Scanner(System.in);

            if (rota.size() >= 2) {
                while (true) {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nTrecho Selecionado: ");
                    for (Integer i : rota) {
                        System.out.print((dados.getDados(0, i)) + ", ");
                    }
                    distanciaTrecho = Calcula.distancia(rota, dados);
                    System.out.println(
                            "\n\nSelecione o tipo de caminhão para o cálculo: \n1 - Pequeno \n2 - Médio \n3 - Grande\n\n\t0 - Encerrar o sistema\t4 - Voltar ao menu");
                    nSelecionado = seletor.nextInt();
                    switch (nSelecionado) {
                        case 0:
                            System.exit(0);
                        case 1:
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println(
                                    "De " + dados.getDados(0, rota.get(0)) + " para " + dados.getDados(0, rota.get(1))
                                            + ", utilizando um caminhão de pequeno porte, a distância é de "
                                            + distanciaTrecho + "Km e o custo será de R$ "
                                            + formatoDinheiro.format(Calcula.custoPequenoPorte(distanciaTrecho))
                                            + ".\n\n");
                            System.out.println("0 - Encerrar o sistema");
                            System.out.println("1 - Voltar");
                            nSelecionado = seletor.nextInt();
                            if (nSelecionado == 0)
                                System.exit(0);
                            if (nSelecionado == 1)
                                break;
                        case 2:
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println(
                                    "De " + dados.getDados(0, rota.get(0)) + " para " + dados.getDados(0, rota.get(1))
                                            + ", utilizando um caminhão de médio porte, a distância é de "
                                            + distanciaTrecho + "Km e o custo será de R$ "
                                            + formatoDinheiro.format(Calcula.custoMedioPorte(distanciaTrecho))
                                            + ".\n\n");
                            nSelecionado = seletor.nextInt();
                            if (nSelecionado == 0)
                                System.exit(0);
                            if (nSelecionado == 1)
                                break;
                        case 3:
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println(
                                    "De " + dados.getDados(0, rota.get(0)) + " para " + dados.getDados(0, rota.get(1))
                                            + ", utilizando um caminhão de grande porte, a distância é de "
                                            + distanciaTrecho + "Km e o custo será de R$ "
                                            + formatoDinheiro.format(Calcula.custoGrandePorte(distanciaTrecho))
                                            + ".\n\n");
                            System.out.println("0 - Encerrar o sistema");
                            System.out.println("1 - Voltar");
                            nSelecionado = seletor.nextInt();
                            if (nSelecionado == 0)
                                System.exit(0);
                            if (nSelecionado == 1)
                                break;
                        case 4:
                            break consultatrecho;
                        default:
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                            Thread.sleep(1500);
                            break;
                    }
                }
            }
            if (seletor.hasNextInt()) {
                nSelecionado = seletor.nextInt();
                if (segundoLoop == true && rota.get(rota.size() - 1) == nSelecionado) {
                    System.out.println(
                            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNão é possível selecionar a mesma cidade em sequência");
                    Thread.sleep(2000);
                    continue;
                }
                if (nSelecionado <= 25 && nSelecionado >= 0) {
                    if (nSelecionado == 0) {
                        System.exit(0);
                    }
                    if (nSelecionado == 25) {
                        break;
                    }
                    rota.add(nSelecionado);
                    segundoLoop = true;
                    dados.printCidades();

                } else {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                    Thread.sleep(1500);
                    dados.printCidades();
                    continue;
                }
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                Thread.sleep(1500);
                dados.printCidades();
                continue;
            }
        }
    }

    private ArrayList<Integer> cadastroRotas() throws FileNotFoundException, InterruptedException {
        ArrayList<Integer> rota = new ArrayList<Integer>();
        boolean segundoLoop = false;
        Dados dados = Dados.getInstance();

        // seletor de cidades para a rota
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSelecione a " + (rota.size() + 1) + "ª cidade:");
            dados.printCidades();
            System.out.println("\n\t25 - Continuar Cadastro");
            System.out.println("\t0 - Encerrar o sistema");
            int nSelecionado;

            seletor = new Scanner(System.in);

            // validacao de tipo de input e valor
            if (seletor.hasNextInt()) {
                nSelecionado = seletor.nextInt();
                if (segundoLoop == true && rota.get(rota.size() - 1) == nSelecionado) {
                    System.out.println(
                            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNão é possível selecionar a mesma cidade em sequência");
                    Thread.sleep(2000);
                    continue;
                }
                if (nSelecionado <= 25 && nSelecionado >= 0) {
                    if (nSelecionado == 0) {
                        seletor.close();
                        System.exit(0);
                    }
                    if (nSelecionado == 25) {
                        if (rota.size() < 2) {
                            System.out.println(
                                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSelecione pelo menos duas cidades antes de prosseguir");
                            Thread.sleep(2000);
                            continue;
                        } else
                            break;
                    }

                    // adiciona a rota selecionada ao CadTransporte
                    rota.add(nSelecionado);
                    segundoLoop = true;
                    continue;
                } else {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                    Thread.sleep(1500);
                    continue;
                }
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSeleção Inválida");
                Thread.sleep(1500);
                continue;
            }

        }
        return rota;
    }

    private void cadastroMercadorias(ArrayList<Integer> rota) throws InterruptedException, FileNotFoundException {
        ArrayList<Item> mercadorias = new ArrayList<Item>();
        CadTransporte transporte = new CadTransporte();

        cadastromercadoria: while (true) {
            transporte.setRota(rota);
            int nSelecionado;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nRota Selecionada:");
            System.out.println(transporte.rotaToString());
            System.out.println("\n\nCarga: \n");
            for (Item item : mercadorias) {
                System.out.println(item.getNome() + " x " + item.quantidade);
            }
            System.out.println("\n0 - Encerrar o sistema");
            System.out.println("1 - Cadastrar mercadoria");
            System.out.println("2 - Remover mercadoria");
            System.out.println("3 - Alterar rota");
            System.out.println("4 - Finalizar e calcular valor");
            seletor = new Scanner(System.in);

            if (seletor.hasNextInt()) {
                nSelecionado = seletor.nextInt();
                switch (nSelecionado) {
                    case 0:
                        seletor.close();
                        System.exit(0);
                    case 1:
                        // recebe nome, peso e quantidade da mercadoria e adiciona ao transporte
                        String nome;
                        Double peso;
                        int quantidade;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInsira um NOME para a mercadoria:");
                        nome = seletor.next();
                        System.out.println(
                                "\nInsira o PESO individual para a mercadoria:\n(Utilize virgula para indicar valores decimais)");
                        if (seletor.hasNextDouble()) {
                            peso = seletor.nextDouble();
                            if (peso <= 0) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                Thread.sleep(1500);
                                continue;
                            }
                            System.out.println("\nInsira quantas UNIDADES desta mercadoria serão transportadas:");
                            if (seletor.hasNextInt()) {
                                quantidade = seletor.nextInt();
                                if (quantidade <= 0) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                    Thread.sleep(1500);
                                    continue;
                                }
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                mercadorias.add(new Item(nome, peso, quantidade));
                            } else {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                Thread.sleep(1500);
                            }
                        } else {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                            Thread.sleep(1500);
                        }
                        break;
                    case 2:
                        // confere se o o usuario ja cadastrou alguma mercadoria
                        if (mercadorias.size() <= 0) {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("Nenhuma mercadoria cadastrada");
                            Thread.sleep(2000);
                            break;
                        }

                        int contador = 1;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("Qual mercadoria deseja remover?");
                        for (Item item : mercadorias) {
                            System.out.println(contador + " - " + item.getNome());
                            contador++;
                        }

                        if (seletor.hasNextInt()) {
                            nSelecionado = seletor.nextInt();
                            if (nSelecionado <= 0) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                Thread.sleep(1500);
                                break;
                            }
                            int indexMercadoria = nSelecionado - 1;
                            if (indexMercadoria > mercadorias.size()) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                Thread.sleep(1500);
                                break;
                            }
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("Quantas unidades deseja remover?");
                            System.out.println("0 - Remover tudo\n");

                            System.out.print(
                                    "Nº de unidades: " + mercadorias.get(indexMercadoria).getQuantidade() + "\n");

                            if (seletor.hasNextInt()) {
                                nSelecionado = seletor.nextInt();
                                if (nSelecionado > mercadorias.get(indexMercadoria).getQuantidade()
                                        || nSelecionado < 0) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                    Thread.sleep(1500);
                                    break;
                                }
                            } else {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                                Thread.sleep(1500);
                                break;
                            }

                            // remove todos as unidades que do item(remove ele da carga)
                            if (nSelecionado == 0) {
                                mercadorias.remove(indexMercadoria);
                            } else {

                                // remove da carga a quantidade indicada pelo usuario
                                mercadorias.get(indexMercadoria)
                                        .setQuantidade(mercadorias.get(indexMercadoria).getQuantidade() - nSelecionado);
                            }
                        } else {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                            Thread.sleep(1500);
                            break;
                        }
                        break;
                    case 3:
                        rota = cadastroRotas();
                        continue cadastromercadoria;
                    case 4:
                        if (mercadorias.size() <= 0) {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("Nenhuma mercadoria cadastrada");
                            Thread.sleep(2000);
                            break;
                        }

                        transporte.setData();

                        // adiciona os itens do ArrayList na classe CadTransporte
                        // ignorando itens em duplicidade
                        for (Item item : mercadorias) {
                            if (transporte.getItems().contains(item)) {
                                continue;
                            }
                            transporte.inserirMercadoria(item);
                        }

                        // arredonda o peso final pra cima.
                        // para casos onde e melhor usar um caminhao mesmo sem enche-lo
                        int pesoFinal = (int) Math.ceil(transporte.getPesoTransporte());

                        // cria um ArrayList para armazenar a frota mais eficiente para o peso
                        // e seta essa frota na classe CadTransporte
                        ArrayList<Integer> frota = new ArrayList<>();
                        frota = Calcula.caminhoesNecessarios(pesoFinal);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        transporte.setVeiculos(frota);

                        // ToString com todos os dados do transporte cadastrado para conferencia
                        System.out.println("\n" + transporte.toString());

                        // permite retornar ao menu para alterar ou confirmar o transporte
                        System.out.println("\n0 - Voltar ao menu\t 1 - Confirmar e Salvar");
                        nSelecionado = seletor.nextInt();
                        if (nSelecionado == 1) {
                            dados.inserirHistorico(transporte);
                            break cadastromercadoria;
                        } else if (nSelecionado == 0) {
                            transporte.limparListaItens();
                            continue cadastromercadoria;
                        }
                    default:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                        Thread.sleep(1500);
                        break;
                }
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                Thread.sleep(1500);
            }
        }
    }

    private void historico() throws InterruptedException {
        ArrayList<CadTransporte> historico = dados.getHistorico();
        int nSelecionado;
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nHistórico de transportes: ");
            if (historico.size() == 0) {
                System.out.println("Nenhum transporte cadastrado até o momento.\n");
            }
            int contador = 1;
            for (CadTransporte i : historico) {
                System.out.println(contador + " - (" + i.dataToString() + ") " + i.rotaToString());
                contador++;
            }
            System.out.println("0 - Voltar ao menu");
            seletor = new Scanner(System.in);

            // validacoes de tipo de input e valor
            if (seletor.hasNextInt() == false) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                Thread.sleep(1500);
                continue;
            }
            nSelecionado = seletor.nextInt();
            if (nSelecionado == 0) {
                break;
            }
            if (nSelecionado < 0 || nSelecionado > historico.size()) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nInválido");
                Thread.sleep(1500);
                continue;
            } else {

                // define e instancia todas as variaveis necessarias
                CadTransporte transporte = dados.consultarHistorico(nSelecionado - 1);

                // arredonda o peso final pra cima.
                // para casos onde e melhor usar um caminhao mesmo sem enche-lo
                int pesoFinal = (int) Math.ceil(transporte.getPesoTransporte());
                ArrayList<Integer> frota = Calcula.caminhoesNecessarios(pesoFinal);
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                transporte.setVeiculos(frota);

                // ToString com todos os dados do transporte selecionado
                System.out.println("\n" + transporte.toString());

                System.out.println("\nInsira qualquer coisa para voltar ao histórico");
                if (seletor.hasNext())
                    continue;
            }
        }
    }

}