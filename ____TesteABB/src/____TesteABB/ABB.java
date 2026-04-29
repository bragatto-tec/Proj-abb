package ____TesteABB;

import java.util.LinkedList;

// Classe ABB<T>: encarregada de manipular a estrutura de dados
// árvore de busca binária (ABB) genérica.
//
// Autor1: Ivan Carlos Alcântara de Oliveira.
// Data da Criação: 04/14/2026. 15h.
public class ABB<T extends Comparable<T>> {   

    // Nó raiz da ABB
    private Node<T> raiz; 

    // Construtor da ABB
    public ABB() {
        raiz = null;
    }

    // Verifica se a ABB está vazia
    public boolean isEmpty() {
        return (raiz == null);
    }

    // Configura a raiz da árvore
    public void setRaiz(Node<T> araiz) {
        raiz = araiz;
    }

    // Obtém o nó raiz da ABB
    public Node<T> getRaiz() {
        return raiz;
    }

    // Procura o elemento e na ABB
    public Node<T> search(T e){
        return search( raiz, e );
    }
    
    // Método que procura o elemento e na ABB de raiz.
    // Retorna null caso o elemento não seja encontrado.
    public Node<T> search( Node<T> node, T e ){
        if (node == null) 
            return null;
        else if (compara(e, node.getValue()) == 0)
            return node;
        else  if (compara(e, node.getValue() ) < 0) 
            return search( node.getFilhoEsquerdo(), e );
        else return search( node.getFilhoDireito(), e );
    }    
    
    // Método público que insere "valor" na ABB.
    // Ou seja, responsável por chamar o método que
    // insere um novo nó (contendo "valor") na ABB de "raiz"
    public T inserir(T valor) {
        try {
            Node<T> novo = new Node<>(valor);
            raiz = inserir(novo, raiz);
            return valor;
        } catch (Exception e) {
            return null;
        }
    }

    // Método que realiza a inserção de um novo nó (novo) na ABB 
    private Node<T> inserir(Node<T> novo, Node<T> atual) {
        if (atual == null) {
            return novo;
        }

        if (compara(novo.getValue(), atual.getValue()) < 0) {
            atual.setFilhoEsquerdo(inserir(novo, atual.getFilhoEsquerdo()));
        } else {
            atual.setFilhoDireito(inserir(novo, atual.getFilhoDireito()));
        }

        return atual;
    }

    // Encarregado de chamar o método que percorre a ABB em
    // emOrdem a partir do raiz
    public String emOrdem() {
        return emOrdem(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // emOrdem2 a partir do raiz
    public void emOrdem2() {
        emOrdem2(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // preOrdem a partir do raiz
    public void preOrdem() {
        preOrdem(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // posOrdem a partir do raiz
    public void posOrdem() {
        posOrdem(raiz);
    }

    // Método que percorre a ABB em Ordem
    // retornando uma String com os valores
    // concatenados do nó
    public String emOrdem(Node<T> no) {
        if (no == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(emOrdem(no.getFilhoEsquerdo()));
        sb.append(no.getValue() + " ");
        sb.append(emOrdem(no.getFilhoDireito()));
        
        return sb.toString();
    }

    // Método que percorre a ABB em Ordem
    // e imprime os valores dos nós
    public void emOrdem2(Node<T> no) {
        if (no != null) {
            emOrdem2(no.getFilhoEsquerdo());
            System.out.print(no.getValue() + "\n");
            emOrdem2(no.getFilhoDireito());
        }
    }

    // Método que percorre a ABB em preOrdem
    // e imprime os valores dos nós
    public void preOrdem(Node<T> no) {
        if (no != null) {
            System.out.print(no.getValue() + "   ");
            preOrdem(no.getFilhoEsquerdo());
            preOrdem(no.getFilhoDireito());
        }
    }

    // Método que percorre a ABB em posOrdem
    // e imprime os valores dos nós
    public void posOrdem(Node<T> no) {
        if (no != null) {
            posOrdem(no.getFilhoEsquerdo());
            posOrdem(no.getFilhoDireito());
            System.out.print(no.getValue() + "   ");
        }
    }

    // Método iterativo que percorre a ABB em Nível e imprime os valores dos nós.
    // Utiliza uma LinkedList funcionando como uma fila auxiliar 
    // (adicionando elementos no final e removendo do começo).
    public void emNivel() {
        if (raiz == null) return;
        Node<T> noAux;
        LinkedList<Node<T>> fila = new LinkedList<Node<T>>();
        fila.addLast(raiz);
        while (!fila.isEmpty()) {
            noAux = fila.pollFirst();
            if (noAux == null) continue;
            if (noAux.getFilhoEsquerdo() != null) {
                fila.addLast(noAux.getFilhoEsquerdo());
            }
            if (noAux.getFilhoDireito() != null) {
                fila.addLast(noAux.getFilhoDireito());
            }
            System.out.print(noAux.getValue() + "   ");
        }
    }

    // Mudança aqui: Método encarregado de chamar o método que percorre a ABB 
    // emOrdem e exibe todos os funcionários, retornando a quantidade (Opção 2)
    public int mostrarTodos() {
        return mostrarTodos(raiz);
    }

    // Mudança aqui: Método que percorre a árvore emOrdem contando e exibindo os nós
    private int mostrarTodos(Node<T> no) {
        if (no == null) {
            return 0;
        }
        
        int qtde = 0;
        qtde = qtde + mostrarTodos(no.getFilhoEsquerdo());
        
        System.out.println(no.getValue());
        qtde = qtde + 1;
        
        qtde = qtde + mostrarTodos(no.getFilhoDireito());
        return qtde;
    }

    // Mudança aqui: Método encarregado de chamar o método que percorre a ABB 
    // emOrdem para somar todos os salários (Opção 3)
    public double calcularSalarios() {
        return calcularSalarios(raiz);
    }

    // Mudança aqui: Método que percorre a árvore emOrdem acumulando os salários
    private double calcularSalarios(Node<T> no) {
        if (no == null) {
            return 0.0;
        }

        double total = 0.0;
        total = total + calcularSalarios(no.getFilhoEsquerdo());

        if (no.getValue() instanceof Funcionario) {
            Funcionario f = (Funcionario) no.getValue();
            total = total + f.getSalario();
        }

        total = total + calcularSalarios(no.getFilhoDireito());
        return total;
    }

    // Mudança aqui: Método encarregado de chamar o método que percorre a ABB 
    // buscando funcionários por um sexo específico (Opção 4)
    public int contarPorSexo(char sexo) {
        return contarPorSexo(raiz, Character.toUpperCase(sexo));
    }

    // Mudança aqui: Método que percorre a árvore emOrdem contando funcionários do sexo informado
    private int contarPorSexo(Node<T> no, char sexo) {
        if (no == null) {
            return 0;
        }

        int count = 0;
        count = count + contarPorSexo(no.getFilhoEsquerdo(), sexo);

        if (no.getValue() instanceof Funcionario) {
            Funcionario f = (Funcionario) no.getValue();
            if (f.getSexo() == sexo) {
                count = count + 1;
            }
        }

        count = count + contarPorSexo(no.getFilhoDireito(), sexo);
        return count;
    }

    // Mudança aqui: Método encarregado de chamar o método que percorre a ABB 
    // buscando funcionários por uma categoria específica (Opção 5)
    public int contarPorCategoria(char categoria) {
        return contarPorCategoria(raiz, Character.toUpperCase(categoria));
    }

    // Mudança aqui: Método que percorre a árvore emOrdem contando funcionários da categoria informada
    private int contarPorCategoria(Node<T> no, char categoria) {
        if (no == null) {
            return 0;
        }

        int count = 0;
        count = count + contarPorCategoria(no.getFilhoEsquerdo(), categoria);

        if (no.getValue() instanceof Funcionario) {
            Funcionario f = (Funcionario) no.getValue();
            if (f.getCategoria() == categoria) {
                count = count + 1;
            }
        }

        count = count + contarPorCategoria(no.getFilhoDireito(), categoria);
        return count;
    }

    // Mudança aqui: Método encarregado de chamar o método que percorre a ABB 
    // e mostra os funcionários que têm idade maior ou igual ao limite (Opção 6)
    public int mostrarPorIdade(int idadeMinima) {
        return contarPorIdade(raiz, idadeMinima);
    }

    // Compatibilidade: wrapper público chamado por Main
    public int contarPorIdade(int idadeMinima) {
        return contarPorIdade(raiz, idadeMinima);
    }

    // Mudança aqui: Método que percorre a árvore emOrdem filtrando os nós pela idade mínima
    private int contarPorIdade(Node<T> no, int idadeMinima) {
        if (no == null) {
            return 0;
        }

        int count = 0;
        count = count + contarPorIdade(no.getFilhoEsquerdo(), idadeMinima);

        if (no.getValue() instanceof Funcionario) {
            Funcionario f = (Funcionario) no.getValue();
            if (f.getIdade() >= idadeMinima) {
                System.out.println(f);
                count = count + 1;
            }
        }

        count = count + contarPorIdade(no.getFilhoDireito(), idadeMinima);
        return count;
    }

    // Método que compara dois objetos do tipo T genérico
    private int compara(T ob1, T ob2) {
        return ob1.compareTo( ob2);
    }
    
    // Determina o menor elemento a partir de um nó
    public Node<T> getMenor(Node<T> node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoEsquerdo() == null) {
            return node;
        } else {
            return getMenor(node.getFilhoEsquerdo());
        }
    }

    // Determina o maior elemento a partir de um nó
    public Node<T> getMaior(Node<T> node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoDireito() == null) {
            return node;
        } else {
            return getMaior(node.getFilhoDireito());
        }
    }

    // Obtém o maior elemento a partir de um nó.
    // Se não tiver mais filho direito e houver um pai, ele assume o filho esquerdo da raiz atual.
    public Node<T> getMax(Node<T> raiz, Node<T> paiRaiz) {
        if (isEmpty()) {
            return null;
        }
        Node<T> aux;
        if (raiz.getFilhoDireito() == null) {
            aux = raiz;
            if (paiRaiz != null) {
                if (paiRaiz.getFilhoEsquerdo() == raiz) {
                    paiRaiz.setFilhoEsquerdo(raiz.getFilhoEsquerdo());
                } else {
                    paiRaiz.setFilhoDireito(raiz.getFilhoEsquerdo());
                }
            }
            return aux;
        } else {
            return getMax(raiz.getFilhoDireito(), raiz);
        }
    }
    
    // Método encarregado de chamar outro método
    // que elimina o objeto e da ABB a partir da raiz
    public boolean eliminar(T e) {
        return eliminar(raiz, null, e);
    }

    // Remove um elemento da árvore e retorna true ou false.
    // Trata os cenários de remoção: quando o nó não possui filhos, possui apenas um, 
    // ou possui ambos, reajustando as referências do pai (deserdando ou adotando o neto).
    // Caso não encontre na raiz atual, prossegue com busca recursiva.
    private boolean eliminar(Node<T> node, Node<T> paiRaiz, T e) {
        Node<T> aux;
        if (node == null) {  
            return false;
        } else { 
            if (compara(e, node.getValue()) == 0) {  
                aux = node;
                if (node.getFilhoEsquerdo() == null && node.getFilhoDireito() == null) {
                    if (paiRaiz == null) {
                        setRaiz(null);
                    } 
                    else {
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(null);
                        } else if (paiRaiz.getFilhoDireito() != null && compara(paiRaiz.getFilhoDireito().getValue(), e) == 0) {
                            paiRaiz.setFilhoDireito(null);
                        }
                    }
                } else if (node.getFilhoDireito() == null) {   
                    if (paiRaiz != null) {
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoEsquerdo());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoEsquerdo());
                        }
                    } 
                    else {
                        Node<T> filho = node.getFilhoEsquerdo();
                        node.setValue(filho.getValue());
                        node.setFilhoEsquerdo(filho.getFilhoEsquerdo());
                        node.setFilhoDireito(filho.getFilhoDireito());
                    }
                } else if (node.getFilhoEsquerdo() == null) {   
                    if (paiRaiz != null) {
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoDireito());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoDireito());
                        }
                    } 
                    else {
                        Node<T> filho = node.getFilhoDireito();
                        node.setValue(filho.getValue());
                        node.setFilhoEsquerdo(filho.getFilhoEsquerdo());
                        node.setFilhoDireito(filho.getFilhoDireito());
                    }
                } else {   
                    aux = getMax(node.getFilhoEsquerdo(), node);
                    node.setValue(aux.getValue());
                }
                aux = null;
                return true;
            } else { 
                if (compara(e, node.getValue()) < 0) {
                    return eliminar(node.getFilhoEsquerdo(), node, e);
                } else { 
                    return eliminar(node.getFilhoDireito(), node, e);
                }
            }
        }
    }
    
    // Algumas implementações de operações com ABBs em forma iterativa:
    
    // Método que procura um objeto (obj) dentro da árvore
    // Retornando o objeto (obj) se encontra ou null, caso contrário
    public Node<T> find(T obj) {
        Node<T> atual = raiz;

        while (atual != null) {
            int cmp = compara(obj, atual.getValue());

            if (cmp == 0) {
                return atual;
            } else if (cmp < 0) {
                atual = atual.getFilhoEsquerdo();
            } else {
                atual = atual.getFilhoDireito();
            }
        }

        return null;
    }

    // Implementação iterativa da Inserção
    public T insert(T valor) {
        try {
            Node<T> novoNodo = new Node<>(valor);

            if (isEmpty()) {
                raiz = novoNodo;
                return valor;
            }

            Node<T> atual = raiz;
            Node<T> pai = null;

            while (atual != null) {
                pai = atual;
                if (compara(valor, atual.getValue()) < 0) {
                    atual = atual.getFilhoEsquerdo();
                } else {
                    atual = atual.getFilhoDireito();
                }
            }

            if (compara(valor, pai.getValue()) < 0) {
                pai.setFilhoEsquerdo(novoNodo);
            } else {
                pai.setFilhoDireito(novoNodo);
            }

            return valor;
        } catch (Exception e) {
            return null;
        }
    }  
}