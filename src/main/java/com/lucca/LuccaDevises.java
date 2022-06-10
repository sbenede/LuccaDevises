package com.lucca;

import com.lucca.devise.Converter;
import com.lucca.devise.model.Exchange;
import com.lucca.devise.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LuccaDevises {

    public static void main(String[] args) throws Exception {
        List<String> lines = FileUtils.parseFile(new File(args[0]));

        // We check if the first line is well-formatted
        String firstLine = lines.get(0);
        StringTokenizer st = new StringTokenizer(firstLine, ";");
        if (st.countTokens() != 3) {
            System.err.println("Impossible de lire la ligne 1");
            throw new Exception("Error while reading file");
        }

        final String fromCurrency = st.nextToken();
        final double amountToConvert = Double.parseDouble(st.nextToken());
        final String toCurrency = st.nextToken();

        // Extract exchanges line by line
        List<Exchange> exchanges = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            st = new StringTokenizer(line, ";");
            if (st.countTokens() != 3) {
                System.err.println("Impossible de lire la ligne " + (i+1));
                throw new Exception("Error while reading file");
            }
            while (st.hasMoreTokens()) {
                exchanges.add(new Exchange(st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken())));
            }
        }

        // We check if the second line is well-formatted
        String secondLine = lines.get(1);
        if (exchanges.size() != Integer.parseInt(secondLine)) {
            System.err.println("Erreur sur la taille du fichier attendue : " + secondLine);
            throw new Exception("File size control error");
        }

        Converter converter = new Converter();
        List<Exchange> finalExchanges = converter.getExchangeToDo(exchanges, fromCurrency, toCurrency);

        int res = converter.convert(finalExchanges, fromCurrency, amountToConvert);
        System.out.println(res);
    }
}
