/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uniba.it.gioco.tipi;


import java.util.List;




/**
 *
 * @author 39379
 */
public class Comando {
        private String name;
        private List<String> aliases;
        private TipoComando type;

        public String getName() {
            return name;
        }

        public List<String> getAliases() {
            return aliases;
        }

        public TipoComando getType() {
            return type;
        }

        public boolean isValid(List<String> tokens) {
            if (tokens.size() < 2 && type == TipoComando.PARAMETERIZED) {
                return false;
            }
            return tokens.size() == 1 || type == TipoComando.PARAMETERIZED;
        }
    }

