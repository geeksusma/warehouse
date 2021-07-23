package es.geeksusma.warehouse.api.dto;

public class ErrorDTO {
    private String code;
    private PointerDTO source;
    private String title;
    private String detail;

    public ErrorDTO() {
    }

    ErrorDTO(String code, String source, String title, String detail) {
        this.code = code;
        this.source = new PointerDTO(source);
        this.title = title;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public PointerDTO getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    private class PointerDTO {
        private String pointer;

        public PointerDTO() {
        }

        public PointerDTO(String pointer) {
            this.pointer = pointer;
        }

        public String getPointer() {
            return pointer;
        }
    }

    public static class ErrorBuilder {

        public static ErrorBuilder builder() {
            return new ErrorBuilder();
        }

        public ErrorDTO build(String code, String source, String title, String detail) {
            return new ErrorDTO(code, source, title, detail);
        }

    }
}
