package Design_Patterns_and_Principles.FactoryMethodPattern;
public class WordDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
