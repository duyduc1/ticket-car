package spring_project.dto;

public class RickShawRequets {
    private Long rickShawId;
    private String fullName;
    private Long phoneNumber;
    private Long yearOfBirth;
    private String descriptions;
    private String gender;
    private String url;
    private String publicId;

    public RickShawRequets() {
    }

    public RickShawRequets(String fullName, Long phoneNumber, Long yearOfBirth, String descriptions, String gender) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.yearOfBirth = yearOfBirth;
        this.descriptions = descriptions;
        this.gender = gender;
    }

    public Long getRickShawId() {
        return rickShawId;
    }

    public void setRickShawId(Long rickShawId) {
        this.rickShawId = rickShawId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}


