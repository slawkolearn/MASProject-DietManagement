package com.slawomirlasik.diet_plan_management.mp3.model.overlapping;

import com.slawomirlasik.diet_plan_management.service.DietManagementService;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class PersonOverlapping extends ExtensionAnnotationAssociationManager implements Serializable {
    private static Integer MINIMAL_AGE = 18;

    public ExtensionAnnotationAssociationManager getAdminRole() {
        return null;
    }

    public ExtensionAnnotationAssociationManager getUserRole() {
        return null;
    }

    enum PersonType {
        DIET_USER,
        DIET_ADMINISTRATOR,
        PERSON
    }

    private EnumSet<PersonType> personTypes = EnumSet.of(PersonType.PERSON);

    private String name;
    private String lastName;

    private LocalDate birthDate;

    public PersonOverlapping(String name, String lastName, LocalDate birthDate) throws Exception {
        super();
        this.name = name;
        this.lastName = lastName;
        setBirthDate(birthDate);
    }

    public Integer getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public static Integer getMinimalAge() {
        return MINIMAL_AGE;
    }

    public static void setMinimalAge(Integer minimalAge) {
        MINIMAL_AGE = minimalAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * the method sets the users current @param birthdate field.
     * <p>
     * used only when some to update incorrect data, and when creating first Person.
     * <p>
     * Throws exception when users age is under 18.
     *
     * @param birthDate
     * @throws Exception
     */

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if (LocalDate.now().getYear() - birthDate.getYear() < 18) {
            throw new Exception(
                    String.format("%s : The %s must have at least %d years old.",
                            ERROR_LABEL, name, MINIMAL_AGE)
            );
        } else {
            this.birthDate = birthDate;
        }
    }

    public void addDietAdministrator(DiplomaOverlapping[] diplomas, DietManagementService service) throws Exception {

        // creating diet administrator
        DietAdministratorOverlapping dietAdmin = new DietAdministratorOverlapping();

        // add diplomas
        for(DiplomaOverlapping diploma : diplomas){
            service.setDiplomaToDietAdministrator(diploma, dietAdmin);
        }

        // add a admin as a link
        // we use method provided by objectplusplus
        this.addLink(roleNameDietAdministrator, roleNameGeneralization, dietAdmin);

        // add flag that this is a DietAdministrator as well
        personTypes.add(PersonType.DIET_ADMINISTRATOR);

    }

    public void addDietUser(DietStatisticsOverlapping[] userStatistics, DietManagementService service) throws Exception {
        // creating diet diet user
        DietUserOverlapping dietUser = new DietUserOverlapping();

        // add dietStatistics
        for(DietStatisticsOverlapping stat : userStatistics){
            service.addUserToStatistic(dietUser, stat);
        }

        // add a admin as a link
        // we use method provided by objectplusplus
        this.addLink(roleNameDietUser, roleNameGeneralization, dietUser);

        // add Diet User person type
        personTypes.add(PersonType.DIET_USER);


    }

    private static String roleNameDietAdministrator = "specializationDietAdministrator";
    private static String roleNameDietUser = "specializationDietUser";
    private static String roleNameGeneralization = "generalizationPerson";


    public void showDiplomas() throws Exception {
        if(personTypes.contains(PersonType.DIET_ADMINISTRATOR)){
            System.out.println("\n");

            ExtensionAssociationManager[] dietAdmin = this.getLinks(roleNameDietAdministrator);

            System.out.println("Printing diplomas for " + this);
            for(ExtensionAssociationManager diploma : ((DietAdministratorOverlapping) dietAdmin[0]).getDietAdministratorDiplomas()){
                System.out.println("      " + diploma);
            }


            System.out.println("\n");
        }else {
            throw new Exception(String.format("%s is not a Diet Administrator ",
                    this));
        }
    }

    public void showStatistics() throws Exception {

        if(personTypes.contains(PersonType.DIET_USER)){
            System.out.println("\n");

            ExtensionAssociationManager[] dietUser = this.getLinks(roleNameDietUser);

            System.out.println("Printing user statistics for " + this);
            for(ExtensionAssociationManager stat : ((DietUserOverlapping) dietUser[0]).getUserDietStatistics()){
                System.out.println("      " + stat);
            }

            System.out.println("\n");

        }else {
            throw new Exception(String.format("%s is not a Diet Administrator ",
                    this));
        }
    }

    public String getInfo() throws Exception {

        String userInfo = "-----------------------------------\n";

        userInfo += this.toString() + "\n";

        if(personTypes.contains(PersonType.DIET_USER)){
            userInfo += ((DietUserOverlapping) getLinks(roleNameDietUser)[0]).getInfo();
        }

        if(personTypes.contains(PersonType.DIET_ADMINISTRATOR)){
            userInfo += ((DietAdministratorOverlapping) getLinks(roleNameDietAdministrator)[0]).getInfo();
        }

        userInfo += "-----------------------------------\n\n";

        return userInfo;
    }

    private List<String> getDiplomas() throws Exception {

        ExtensionAssociationManager[] diplomas = getLinks(roleNameDietAdministrator);

        List<String> diplomasList = new ArrayList<>();

        for(ExtensionAssociationManager diploma : diplomas){
            diplomasList.add(diploma.toString());
        }

        return diplomasList;
    }

    private List<String> getStatistics() throws Exception {
        ExtensionAssociationManager[] dietUserStatistics = getLinks(roleNameDietUser);

        List<String> userStatsList = new ArrayList<>();

        for(ExtensionAssociationManager stat : dietUserStatistics){
            userStatsList.add(stat.toString());
        }

        return userStatsList;
    }


    @Override
    public String toString() {
        return "Personal Data={" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

