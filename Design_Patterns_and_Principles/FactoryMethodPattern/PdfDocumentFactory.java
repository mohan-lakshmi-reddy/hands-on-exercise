package Design_Patterns_and_Principles.FactoryMethodPattern;
public class PdfDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}