import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AtividadeClassesEObjetos {
    public static void main(String[] args) {
        
        //Criação de uma lista de clientes
        List<Cliente> clientes = new ArrayList<Cliente>();
        //Criação de uma lista de consultas
        List<Consulta> consultas = new ArrayList<Consulta>();
        
        //Criação de um cliente
        Calendar dataNascimentoCliente = Calendar.getInstance();
        dataNascimentoCliente.set(1990, 10, 10);
        Cliente cliente = new Cliente("João", "Rua 1", dataNascimentoCliente, 
        'M', 100);

        System.out.println("PRINTANDO INFORMAÇÕES DO CLIENTE");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Data de nascimento: " + cliente.getDataNascimento().getTime());
        System.out.println("Sexo: " + cliente.getSexo());
        System.out.println("Valor da tarifa: " + cliente.getValorTarifa());
        System.out.println("Idade: " + cliente.calcularIdade());
        clientes.add(cliente);

        //Criação de um cliente dependente
        System.out.println("PRINTANDO INFORMAÇÕES DO CLIENTE DEPENDENTE");
        Calendar dataNascimentoClienteDependente = Calendar.getInstance();
        dataNascimentoClienteDependente.set(2000, 10, 10);
        Cliente clienteDependente = new Cliente("Maria", "Rua 1", 
        dataNascimentoClienteDependente, 'F', 50);
        System.out.println("Nome: " + clienteDependente.getNome());
        System.out.println("Endereço: " + clienteDependente.getEndereco());
        System.out.println("Data de nascimento: " + clienteDependente.getDataNascimento().getTime());
        System.out.println("Sexo: " + clienteDependente.getSexo());
        System.out.println("Valor da tarifa: " + clienteDependente.getValorTarifa());
        System.out.println("Idade: " + clienteDependente.calcularIdade());
        clientes.add(clienteDependente);        
        
        //Criação de um medico
        System.out.println("PRINTANDO INFORMAÇÕES DO MÉDICO");
        Calendar dataNascimentoMedico = Calendar.getInstance();
        dataNascimentoMedico.set(1980, 10, 10);
        ProfissionalSaude medico = new ProfissionalSaude("Pedro", "Rua 2",
         dataNascimentoMedico, 'M', "Cardiologista", "CRM123");
        System.out.println("Nome: " + medico.getNome());
        System.out.println("Endereço: " + medico.getEndereco());
        System.out.println("Data de nascimento: " + medico.getDataNascimento().getTime());
        System.out.println("Sexo: " + medico.getSexo());
        System.out.println("Especialidade: " + medico.getEspecialidade());
        System.out.println("CRM: " + medico.getCrm());

        //Criação de uma consulta
        System.out.println("PRINTANDO INFORMAÇÕES DA CONSULTA");
        Calendar dataConsulta = Calendar.getInstance();
        dataConsulta.set(2020, 10, 10);
        Consulta consulta = new Consulta(dataConsulta, "Consulta de rotina", medico, cliente);
        System.out.println("Data da consulta: " + consulta.getData().getTime());
        System.out.println("Descrição: " + consulta.getDescricao());
        System.out.println("Médico: " + consulta.getProfissionalSaude().getNome());
        System.out.println("Cliente: " + consulta.getCliente().getNome());
        consultas.add(consulta);
        
        //Obtem as tarifas dos clientes
        double valorTarifa = 0;
        for (Cliente clienteFatura : clientes) {
            valorTarifa += clienteFatura.getValorTarifa();
        }

        //Cria a fatura
        Calendar dataVencimento = Calendar.getInstance();
        dataVencimento.set(2020, 11, 10);
        
        //Cria uma fatura com as tarifas dos clientes
        Fatura fatura = new Fatura(valorTarifa, dataVencimento, 2, 0.1);

        //Calcula o valor da fatura com multa e juros
        double valorFatura = fatura.calcularValorFatura();        
        System.out.println("Valor da fatura: " + valorFatura);

        //Cria um contrato
        Calendar dataInicioContrato = Calendar.getInstance();
        dataInicioContrato.set(2023, 01, 10);
        Contrato contrato = new Contrato(dataInicioContrato, clientes, consultas, fatura);
        System.out.println("PRINTANDO INFORMAÇÕES DO CONTRATO");
        System.out.println("Data de início do contrato: " + contrato.getDataInicio().getTime());
        System.out.println("Clientes: " + contrato.getClientes().size());
        System.out.println("Consultas: " + contrato.getConsultas().size());
        System.out.println("Fatura: " + contrato.getFatura().getValorTotal());                
    }
}

/*
Para associar-se ao plano de saúde, faz-se necessário a celebração de um contrato entre a empresa 
prestadora do serviço de plano de saúde e a pessoa interessada. Este contrato deve possuir ao menos uma pessoa
vinculada, que será a titular do plano. Outras pessoas podem ser incluídas neste contrato 
(no momento da celebração do contrato ou posteriormente), sendo chamadas de dependentes. 
Cada pessoa do contrato deve ser tarifada de forma individual, sendo os preços definidos no momento do 
fechamento do contrato ou momento da inclusão, de acordo com o sexo e a idade de cada um. 
Estes valores serão reajustados anualmente, de acordo com autorização da Anvisa. Um contrato deve gerar
uma única fatura mensal, em que serão incluídas as tarifas do titular e dos dependentes, caso existam. 
Esta fatura deve possuir uma data de vencimento, percentual de multa e juros por dia. 
Devem ser mantidos os históricos das consultas e exames realizados por cada uma das pessoas do contrato, 
incluindo os profissionais de saúde que realizaram as consultas e exames. 
Para as pessoas, devem ser armazenados o nome, endereço, data de nascimento e sexo. 
*/

//Classe pessoa
class Pessoa{
    protected String nome;
    protected String endereco;
    protected Calendar dataNascimento;
    protected char sexo;
    protected List<Consulta> historicoConsultas;

    //Construtor
    public Pessoa(String nome, String endereco, Calendar dataNascimento, char sexo) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    //#region Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    //#endregion
}

//Classe cliente, que herda de pessoa
class Cliente extends Pessoa{
    private double valorTarifa;
    //Construtor
    public Cliente(String nome, String endereço, Calendar dataNascimento, char sexo, double valorTarifa) { 
        super(nome, endereço, dataNascimento, sexo);
        this.valorTarifa = valorTarifa;       
    }    

    //Método para calcular a idade do cliente
    public int calcularIdade(){
        Calendar dataAtual = Calendar.getInstance();
        int idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)){
            idade--;
        } else if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)){
            idade--;
        }
        return idade;
    }

    //Método para calcular o valor da tarifa do cliente
    public double calcularValorTarifa(){
        double valorTarifa = 0;
        int idade = calcularIdade();
        if (idade < 18){
            valorTarifa = 0;
        } else if (idade >= 18 && idade <= 23){
            if (sexo == 'M'){
                valorTarifa = 100;
            } else if (sexo == 'F'){
                valorTarifa = 80;
            }
        } else if (idade >= 24 && idade <= 30){
            if (sexo == 'M'){
                valorTarifa = 120;
            } else if (sexo == 'F'){
                valorTarifa = 100;
            }
        } else if (idade >= 31 && idade <= 40){
            if (sexo == 'M'){
                valorTarifa = 150;
            } else if (sexo == 'F'){
                valorTarifa = 130;
            }
        } else if (idade >= 41 && idade <= 50){
            if (sexo == 'M'){
                valorTarifa = 200;
            } else if (sexo == 'F'){
                valorTarifa = 180;
            }
        } else if (idade >= 51 && idade <= 60){
            if (sexo == 'M'){
                valorTarifa = 250;
            } else if (sexo == 'F'){
                valorTarifa = 230;
            }
        } else if (idade > 60){
            if (sexo == 'M'){
                valorTarifa = 300;
            } else if (sexo == 'F'){
                valorTarifa = 280;
            }
        }
        return valorTarifa;
    }

    //#region Getters e setters
    public double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }
    //#endregion
}

//Classe profissional saude, que herda de pessoa
class ProfissionalSaude extends Pessoa{
    private String especialidade;
    private String crm;
    
    //Construtor
    public ProfissionalSaude(String nome, String endereço, Calendar dataNascimento, char sexo, String especialidade, String crm) {
        super(nome, endereço, dataNascimento, sexo);
        this.especialidade = especialidade;
        this.crm = crm;
    }

    //#region Getters e setters

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    //#endregion
}

//Classe consulta
class Consulta{
    private Calendar data;
    private String descricao;
    private ProfissionalSaude profissionalSaude;
    private Cliente cliente;

    //Construtor
    public Consulta(Calendar data, String descricao, ProfissionalSaude profissionalSaude, Cliente cliente) {
        this.data = data;
        this.descricao = descricao;
        this.profissionalSaude = profissionalSaude;
        this.cliente = cliente;
    }

    //#region Getters e setters
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    //#endregion
}

//Classe fatura
class Fatura{
    private double valorTotal;
    private Calendar dataVencimento;
    private double percentualMulta;
    private double jurosPorDia;

    //Construtor
    public Fatura(double valorTotal, Calendar dataVencimento, double percentualMulta, double jurosPorDia) {
        this.valorTotal = valorTotal;
        this.dataVencimento = dataVencimento;
        this.percentualMulta = percentualMulta;
        this.jurosPorDia = jurosPorDia;
    }
    

    //Método para calcular o valor da fatura com multa e juros
    public double calcularValorFatura(){
        double valorFatura = 0;
        Calendar dataAtual = Calendar.getInstance();
        int diasAtraso = dataAtual.get(Calendar.DAY_OF_YEAR) - dataVencimento.get(Calendar.DAY_OF_YEAR);
        if (diasAtraso > 0){
            valorFatura = valorTotal + (valorTotal * percentualMulta) + (jurosPorDia * diasAtraso);
        } else {
            valorFatura = valorTotal;
        }
        return valorFatura;
    }    

    //#region Getters e setters

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }        

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getPercentualMulta() {
        return percentualMulta;
    }

    public void setPercentualMulta(double percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public double getJurosPorDia() {
        return jurosPorDia;
    }

    public void setJurosPorDia(double jurosPorDia) {
        this.jurosPorDia = jurosPorDia;
    }
    //#endregion
}

//Classe contrato
class Contrato{
    private Calendar dataInicio;    
    private List<Cliente> clientes;
    private List<Consulta> consultas;
    private Fatura fatura;

    //Construtor
    public Contrato(Calendar dataInicio, List<Cliente> clientes, List<Consulta> consultas, Fatura fatura) {
        this.dataInicio = dataInicio;
        this.clientes = clientes;
        this.consultas = consultas;
        this.fatura = fatura;
    }

    public void addPessoa(Pessoa pessoa) {
        if (pessoa instanceof Cliente) {
            clientes.add((Cliente) pessoa);
        } 
    }
    
    //Calcula a data de vencimento da fatura, a partir da data de vencimento do contrato
    public Calendar calcularDataVencimentoFatura(){
        Calendar dataVencimentoFatura = Calendar.getInstance();
        dataVencimentoFatura.setTime(dataInicio.getTime());
        dataVencimentoFatura.add(Calendar.DAY_OF_MONTH, 30);
        return dataVencimentoFatura;
    }
    
    //#region Getters e setters
    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }
    //#endregion
}