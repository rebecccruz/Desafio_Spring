package com.github.transformeli.desafiospring.repository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.transformeli.desafiospring.model.Articles;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ArticlesRepository {
    private final String linkfile = "src/main/resources/products.json";

    public List<Articles> getAllArticles() {
        return readFile();
    }


    public Articles getByCategory(String category) {
        try{
            List<Articles> lista = readFile();
            for (Articles a : lista) {
                if (a.getCategory().equals(category)) {
                    return a;
                }
            }
        }
        catch(Exception e){

        }

    }

    public void saveArticles(Articles articles){
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Articles> listaArticles, listaArticlesNova;
        try{
            listaArticles = readFile();
            listaArticlesNova = new ArrayList<>(listaArticles);
            listaArticlesNova.add(articles);
            writer.writeValue(new File(linkfile), listaArticlesNova);
        }catch(Exception e){
            System.out.println("Erro ao inserir as informacoes");
        }
    }

    private List<Articles> readFile(){
        ObjectMapper mapper = new ObjectMapper();
        List<Articles> lista = null;
        try{
            lista = Arrays.asList(mapper.readValue(new File(linkfile),Articles[].class));
        }
        catch(Exception e){
            lista = new ArrayList<>();
        }
        return lista;
    }

}
