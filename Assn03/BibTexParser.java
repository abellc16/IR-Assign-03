// Authors: Camby Abell, Jamie Rios, Tim Gwaltney
// Assignment: Programming Assignment 03
// Filename: SearchSolution.java

/**
 * This file will contain our BibTex Parser.
 */

public class BibTexParser {

    public BibTexParser () {
        BibTeXParser parse = new BibTeXParser();
        BibTeXDatabase database = parse.parse(new BufferedReader(new FileReader(corpus)));

        java.util.Map<Key, BibTeXEntry> map = database.getEntries();
        for(BibTeXEntry ent : map.values()) {
            Document doc = new Document();

            doc.add(new TextField("type", ent.getType().getValue(), Field.Store.YES));
            doc.add(new TextField("key", ent.getKey().getValue(), Field.Store.YES));

            java.util.Map<Key,Value> fields = ent.getFields();
            for(Key key : fields.keySet()) {
                if (key.getValue().equals("abstract")) {
                    FieldType fType = new FieldType();
                    fType.setStored(true);
                    fType.setTokenized(true);
                    fType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
                    fType.setStoreTermVectors(true);
                    fType.setStoreTermVectorPositions(true);
                    doc.add(new Field(key.getValue(), fields.get(key).toUserString(), fType));
                    continue;
                }
                doc.add(new TextField(key.getValue(), fields.get(key).toUserString(), Field.Store.YES));
            }
        }
    }
}