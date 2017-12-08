package view;

import model.bean.Categoria;
import model.bean.Item;
import model.dao.CategoriaDAO;
import model.dao.ItemDAO;

public class TestArea {
    
    public static void main(String[] args){
        
        // CRUD da Tabela Categoria
      /*  try {
            
            // LEVA EM CONSIDERAÇÃO QUE AS TABELAS ESTÂO VAZIAS 
        
            Categoria cat1 = new Categoria("Filmes");
            Categoria cat2 = new Categoria("Doces");
            CategoriaDAO daoCat = new CategoriaDAO();

            System.out.println("CATEGORIA");

            System.out.println("INSERT CATEGORIA");
            daoCat.inserir(cat1);
            daoCat.inserir(cat2);

            System.out.println("LISTAR CATEGORIA");
            for(Categoria c : daoCat.selectCategoria()){
                System.out.println("Descrição: " + c.getDescricao());
            }

            System.out.println("UPDATE CATEGORIA");
            cat1.setDescricao("Series");
            cat1.setId(2);
            daoCat.atualizar(cat1);
            
            System.out.println("DELETE CATEGORIA");
            cat1.setId(1);
            daoCat.excluir(cat1); 
            
            System.out.println("================================================\n");
            
        } catch (Exception ex) {
            System.err.println("ERRO! : " + ex.getMessage());
        }
        */
      
      // CRUD da Tabela Item
      try{
          
          /* Leva em consideração que a tabela de categoria esta vazia */
          Categoria cat = new Categoria("Tecnologia");
          
          CategoriaDAO daoCat = new CategoriaDAO();
          daoCat.inserir(cat);
          
          cat.setId(1);
          System.out.println("ITENS");
          
          System.out.println("INSERT");
          Item item = new Item("Notebook", 2, 5000, cat);
          ItemDAO daoItem = new ItemDAO();
          daoItem.inserir(item);
          
          System.out.println("SELECT");
          for(Item i : daoItem.selectItem()){
              System.out.println("Item: " + i.getDescricao() + " - Categoria: " + i.getCategoria().getDescricao());
          }
          
          System.out.println("UPDATE");
          Categoria newCat = new Categoria("Video Games");
          daoCat.inserir(newCat);
          
          newCat.setId(2);
          
          item.setDescricao("Play Station IV");
          item.setQtd(5);
          item.setValor(2500.89);
          item.setCategoria(newCat);
          item.setId(1);
          
          daoItem.atualizar(item);
          
          System.out.println("DELETE");
          daoItem.excluir(item);
          
      }catch(Exception ex){
          System.err.println("ERROR! : " + ex.getMessage());
      }
    }
}
