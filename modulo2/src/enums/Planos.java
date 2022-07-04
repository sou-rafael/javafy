package enums;

public enum Planos {

    PREMIUM(1), BASICO(0);

    private Integer escolha;
    Planos(Integer escolha){
        this.escolha = escolha;
    }

    public Integer getEscolha (){
        return escolha;
    }
}
