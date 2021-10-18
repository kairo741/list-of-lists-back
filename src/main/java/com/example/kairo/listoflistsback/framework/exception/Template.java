package com.example.kairo.listoflistsback.framework.exception;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Template {

    private final String template;
    private final Set<String> tags;

    public Template(String template) {
        this.template = template;
        this.tags = new TreeSet<>();
        StringBuilder nomeVariavel = null;
        boolean variavel = false;
        for (char c : template.toCharArray()) {
            if (!variavel && c == '{') {
                variavel = true;
                nomeVariavel = new StringBuilder();
            } else if (variavel && c == '}') {
                variavel = false;
                tags.add(nomeVariavel.toString());
                nomeVariavel = null;
            } else if (variavel) {
                nomeVariavel.append(c);
            }
        }
    }

    public Set<String> getTags() {
        return tags;
    }

    public String substituir(Map<String, String> substituicoes) {
        String texto = template;
        for (Map.Entry<String, String> entry : substituicoes.entrySet()) {
            texto = texto.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        for (String tag : tags) {
            if (substituicoes.containsKey(tag)) continue;
            texto = texto.replace("{" + tag + "} ", "");
            texto = texto.replace("{" + tag + "}", "");
        }
        return texto;
    }
}
