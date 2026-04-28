package mackbemvindo;

public class Funcionario implements Comparable<Funcionario> {

    private int    id;        
    private char   categoria; 
    private String nome;      
    private String cargo;     
    private char   sexo;      
    private int    idade;     
    private double salario;   

    public Funcionario(int id, char categoria, String nome, String cargo,
                       char sexo, int idade, double salario) {
        this.id        = id;
        this.categoria = Character.toUpperCase(categoria);
        this.nome      = nome;
        this.cargo     = cargo;
        this.sexo      = Character.toUpperCase(sexo);
        this.idade     = idade;
        this.salario   = salario;
    }


    public int    getId()        { return id; }
    public void   setId(int id)  { this.id = id; }

    public char   getCategoria()          { return categoria; }
    public void   setCategoria(char c)    { this.categoria = Character.toUpperCase(c); }

    public String getNome()               { return nome; }
    public void   setNome(String nome)    { this.nome = nome; }

    public String getCargo()              { return cargo; }
    public void   setCargo(String cargo)  { this.cargo = cargo; }

    public char   getSexo()               { return sexo; }
    public void   setSexo(char sexo)      { this.sexo = Character.toUpperCase(sexo); }

    public int    getIdade()              { return idade; }
    public void   setIdade(int idade)     { this.idade = idade; }

    public double getSalario()            { return salario; }
    public void   setSalario(double s)    { this.salario = s; }


    public String descCategoria() {
        switch (categoria) {
            case 'P': return "Presencial";
            case 'O': return "Home Office";
            case 'H': return "Híbrido";
            default:  return "Desconhecida";
        }
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %05d | %-20s | Cargo: %-18s | Sexo: %c | Idade: %3d | " +
            "Categoria: %-10s | Salário: R$ %,.2f",
            id, nome, cargo, sexo, idade, descCategoria(), salario
        );
    }

    @Override
    public int compareTo(Funcionario outro) {
        return Integer.compare(this.id, outro.id);
    }
}