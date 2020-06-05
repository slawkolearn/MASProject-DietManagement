package com.slawomirlasik.diet_plan_management.util;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

// TODO:SL TODO ASAP!! separate adding association by type -> one associationType one method
// TODO:SL check whether multiple association are handled properly
// TODO:SL add method that add all possible associations between given two objects
// TODO:SL make all methods from util classes at least proteted / package private so the classes that extends them or are in the same packge can use them (we assume that they know what they will do)

public class ExtensionAnnotationAssociationManager extends ExtensionAssociationManager implements Serializable {

    public ExtensionAnnotationAssociationManager() {
        super();
    }

    // TODO:SL add a way for using the same role names for different associations (role names should be not unique)

    public static <T extends ExtensionAnnotationAssociationManager> String getRoleNameForTarget(
            T source,
            Class<? extends ExtensionAnnotationAssociationManager> targetClass) {
        Annotation[] sourceAnnotations = source.getClass().getAnnotations();

        for (Annotation annotation : sourceAnnotations) {
            try {
                Method targetMethod = annotation.annotationType().getMethod("target");
                Class<?> sourceTargetClass = (Class<?>) targetMethod.invoke(annotation);

                if (sourceTargetClass.equals(targetClass)) {
                    System.out.println("sourceTargetClass : " + sourceTargetClass);
                    return (String) annotation.annotationType().getMethod("role").invoke(annotation);
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    @Deprecated
    private <T extends ExtensionAnnotationAssociationManager> void addLink(
            T target
    ) throws Exception {
        Class<? extends ExtensionAnnotationAssociationManager> sourceClass = this.getClass();
        Class<? extends ExtensionAnnotationAssociationManager> targetClass = target.getClass();

        // now we must find annotation that corresponds to one another

        ArrayList<Annotation[]> associatedAnnotationsFromSourceAndTarget =
                getAssociatedAnnotationsFromSourceAndTarget(sourceClass, targetClass);

        associatedAnnotationsFromSourceAndTarget.stream().forEach(
                e -> Arrays.stream(e).forEach(System.out::println)
        );

        System.out.println("---------------------------------------------------");

        // TODO:SL make possible to create multiple associations between given objects at a time (try to call all association creattion methods)
        if (associatedAnnotationsFromSourceAndTarget.size() > 1) {
            // throw Excption -> multpiple variants of associations between classes. User must be more specific
            // (different methods)
            throw new Exception(
                    String.format("Multiple associations options between %s and %s. " +
                                    "Specify which association should be used!",
                            sourceClass.getSimpleName(),
                            targetClass.getSimpleName())
            );
        }

        // now we are cleared to prepare the association
        // check if the association can be created based on the current association state created
        //
        // check cardinality
        //
        // TODO:SL returns always true - imlement this feature in the future :)
        if (!cardinalityAreMet(associatedAnnotationsFromSourceAndTarget.get(0))) {
            throw new Exception(
                    String.format("Cardinality are not met between %s and %s. ",
                            sourceClass.getSimpleName(),
                            targetClass.getSimpleName())
            );
        }

        // Compose the association
        String sourceRoleName = (String) associatedAnnotationsFromSourceAndTarget.get(0)[0].
                annotationType().
                getMethod("role").
                invoke(associatedAnnotationsFromSourceAndTarget.get(0)[0]);

        String targetRoleName = (String) associatedAnnotationsFromSourceAndTarget.get(0)[1].
                annotationType().
                getMethod("role").
                invoke(associatedAnnotationsFromSourceAndTarget.get(0)[1]);

        // get the possible qualifier - for now always String type
        boolean qualifiedAssociationCreated = ifPossibleMakeQualifiedAssociationBetweenObjects(
                this,
                target,
                associatedAnnotationsFromSourceAndTarget.get(0)[0],
                associatedAnnotationsFromSourceAndTarget.get(0)[1],
                sourceRoleName,
                targetRoleName
        );

        // if qualifier association created do not create normal associaoin

        if (!qualifiedAssociationCreated) {
            System.out.println("Created Normal Binary association");
            addLink(sourceRoleName, targetRoleName, target);
        } else {
            System.out.println("Created Qualified association");
        }
        ;


    }


    /**
     * Tries to created qualified association between given objects, and if created return true
     * using roles given if not possible return false;
     *
     * @param object1
     * @param object2
     * @param annotation1
     * @param annotation2
     * @param sourceRoleName
     * @param targetRoleName
     * @return
     */
    private <T extends ExtensionAnnotationAssociationManager> boolean ifPossibleMakeQualifiedAssociationBetweenObjects(
            T object1,
            T object2,
            Annotation annotation1,
            Annotation annotation2,
            String sourceRoleName,
            String targetRoleName
    ) throws InvocationTargetException, IllegalAccessException {


        // check if one of them contains OneToManyAssociation annotation
        // if so thech if the flag of this annotation is qualified is true
        // if so retrieve the qualifier from the owner object of this annotation
        if (annotation1.annotationType().equals(OneToManyAssociation.class)) {
            System.out.println("Yep1 : " + annotation1 + " " + object1);

            // if so thech if the flag of this annotation is qualified is true
            OneToManyAssociation oneToManyAssociationAnnotation1 = (OneToManyAssociation) annotation1;
            if (oneToManyAssociationAnnotation1.qualified()) {
                // if so retrieve the qualifier from the owner object of this annotation
                // get annotated method by @Qualifier annotation
                Method[] object1Methods = object1.getClass().getMethods();
                for (Method method : object1Methods) {
                    if (method.isAnnotationPresent(Qualfier.class)) {
                        // make qualified association
                        // from object2
                        object2.addLink(targetRoleName, sourceRoleName, object1, method.invoke(object1));
                        // return true
                        return true;
                    }
                }
            }
        }
        if (annotation2.annotationType().equals(OneToManyAssociation.class)) {
            System.out.println("Yep2 : " + annotation2 + " " + object2);

            // if so thech if the flag of this annotation is qualified is true
            OneToManyAssociation oneToManyAssociationAnnotation2 = (OneToManyAssociation) annotation2;
            if (oneToManyAssociationAnnotation2.qualified()) {
                // if so retrieve the qualifier from the owner object of this annotation
                // get annotated method by @Qualifier annotation
                Method[] object2Methods = object2.getClass().getMethods();
                for (Method method : object2Methods) {
                    if (method.isAnnotationPresent(Qualfier.class)) {
                        // make qualified association
                        // from object
                        object1.addLink(sourceRoleName, targetRoleName, object2, method.invoke(object2));
                        // return true
                        return true;
                    }
                }
            }

        }

        // return false if no qualified association created
        return false;

    }

    // TODO:SL Implement cardinalityAreMet method
    private boolean cardinalityAreMet(Annotation[] annotations) {

        return true;
    }


    /**
     * Returns ArrayList with proper association types
     * Like OneToMany - ManyToOne
     * ManyToOne - OneToMany
     * ManyToMany - ManyToMany
     * ManyToManyAssociationWithAttribute - ManyToManyAssociationWithAttribute
     * <p>
     * pairs
     * <p>
     * After this method user knows that returned association pairs have something in common..
     * Also when returning size is more than one there is ambiguity of association at this point and user must be
     * more specific (use different method to create appropriate association between object)
     *
     * @param sourceClass
     * @param targetClass
     * @return
     */
    private ArrayList<Annotation[]> getAssociatedAnnotationsFromSourceAndTarget(
            Class<? extends ExtensionAnnotationAssociationManager> sourceClass,
            Class<? extends ExtensionAnnotationAssociationManager> targetClass) {

        ArrayList<Annotation[]> correspondedAnnotationPairs = new ArrayList<>();

        System.out.println(sourceClass);
        System.out.println(targetClass);


        for (Annotation sourceAnnotation : sourceClass.getAnnotations()) {
            System.out.println("sourceAnnotation after for: " + sourceAnnotation.annotationType().getSimpleName()
                    + " " + sourceAnnotation);
            // TODO:SL here must be second loop to check the targetAnnotations for possible pair - loop must be for possible multpiple association types on one object
            // TODO:SL THIS WORKS ONLY WHEN A CLASS HAS ONE ASSOCIATION TYPE MAXIMUM - another loop needed for many possible pairs

            switch (sourceAnnotation.annotationType().getSimpleName()) {
                // many to many has two possibilities on the other side (many to many , or AttributeClass)
//                case "AttributeClass":
                case "ManyToManyAssociation":
                    // so n target must be ManyToMany Association
                    ManyToManyAssociation sourceManyToManyAssociationAnnotation = (ManyToManyAssociation) sourceAnnotation;
                    if (targetClass.isAnnotationPresent(ManyToManyAssociation.class)) {
                        System.out.println("Yes it is ManyToManyAssociation present");
                        ManyToManyAssociation targetManyToManyAssociationAnnotation = targetClass.getAnnotation(ManyToManyAssociation.class);

                        System.out.println(sourceManyToManyAssociationAnnotation);
                        System.out.println(targetManyToManyAssociationAnnotation);

                        // check if those annoations concerns each other

                        if (
                                sourceManyToManyAssociationAnnotation.target().equals(targetClass) &&
                                        targetManyToManyAssociationAnnotation.target().equals(sourceClass)
                        ) {
                            System.out.println("added");

                            correspondedAnnotationPairs.add(new Annotation[]{
                                    sourceManyToManyAssociationAnnotation,
                                    targetManyToManyAssociationAnnotation
                            });
                        }

                    }
                    break;
                case "ManyToOneAssociation":
                    // so n target must be OneToMany Association
                    ManyToOneAssociation sourceManyToOneAssociationAnnotation = (ManyToOneAssociation) sourceAnnotation;
                    if (targetClass.isAnnotationPresent(OneToManyAssociation.class)) {
                        System.out.println("Yes it is OneToManyAssociation present");
                        OneToManyAssociation targetOneToManyAssociationAnnotation = targetClass.getAnnotation(OneToManyAssociation.class);

                        System.out.println(sourceManyToOneAssociationAnnotation);
                        System.out.println(targetOneToManyAssociationAnnotation);

                        // check if those annoations concerns each other

                        if (
                                sourceManyToOneAssociationAnnotation.target().equals(targetClass) &&
                                        targetOneToManyAssociationAnnotation.target().equals(sourceClass)
                        ) {
                            System.out.println("added");

                            // if so add the pair for returning
                            correspondedAnnotationPairs.add(new Annotation[]{
                                    sourceManyToOneAssociationAnnotation,
                                    targetOneToManyAssociationAnnotation
                            });
                        }

                    }
                    break;
                case "OneToManyAssociation":
                    OneToManyAssociation sourceOneToManyAssociationAnnotation = (OneToManyAssociation) sourceAnnotation;
                    if (targetClass.isAnnotationPresent(ManyToOneAssociation.class)) {
                        System.out.println("Yes it is ManyToOneAssociation present");
                        ManyToOneAssociation targetManyToOneAssociationAnnotation = targetClass.getAnnotation(ManyToOneAssociation.class);

                        System.out.println(sourceOneToManyAssociationAnnotation);
                        System.out.println(targetManyToOneAssociationAnnotation);


                        // check if those annoations concerns each other

                        if (
                                sourceOneToManyAssociationAnnotation.target().equals(targetClass) &&
                                        targetManyToOneAssociationAnnotation.target().equals(sourceClass)
                        ) {
                            System.out.println("added");
                            correspondedAnnotationPairs.add(new Annotation[]{
                                    sourceOneToManyAssociationAnnotation,
                                    targetManyToOneAssociationAnnotation
                            });
                        }
                    }
                    break;
//                case "ManyToManyAssociationWithAttribute":
//                    ManyToManyAssociationWithAttribute sourceManyToManyAssociationWithAttributeAnnotation =
//                            (ManyToManyAssociationWithAttribute) sourceAnnotation;
//                    if (targetClass.isAnnotationPresent(ManyToManyAssociationWithAttribute.class)) {
//                        System.out.println("Yes it is ManyToManyAssociationWithAttribute present");
//                        ManyToManyAssociationWithAttribute targetManyToManyAssociationWithAttributeAnnotation =
//                                targetClass.getAnnotation(ManyToManyAssociationWithAttribute.class);
//
//                        System.out.println(sourceManyToManyAssociationWithAttributeAnnotation);
//                        System.out.println(targetManyToManyAssociationWithAttributeAnnotation);
//
//
//                        // check if those annoations concerns each other
//
//                        if (
//                                sourceManyToManyAssociationWithAttributeAnnotation.target().equals(targetClass) &&
//                                        targetManyToManyAssociationWithAttributeAnnotation.target().equals(sourceClass)
//                        ) {
//                            System.out.println("added");
//
//                            correspondedAnnotationPairs.add(new Annotation[]{
//                                    sourceManyToManyAssociationWithAttributeAnnotation,
//                                    targetManyToManyAssociationWithAttributeAnnotation
//                            });
//                        }
//
//                    }
//                    break;
                default:
                    break;
            }
        }

        return correspondedAnnotationPairs;

    }

    public static <T extends ExtensionAnnotationAssociationManager> boolean wholeIsValid(
            Class<? extends ExtensionAnnotationAssociationManager> partClass,
            T wholeObject) {

        // check if whole is not null
        if (wholeObject == null) {
            return false;
        }

        // check if whole is appropriate class type
        if (
                wholeObject.getClass().getAnnotationsByType(CompositionWhole.class).length > 0
        ) {
            //     partClass.equals(wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget())
            // wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget().equals(partClass)
            for (CompositionWhole compositionWhole : wholeObject.getClass().getAnnotationsByType(CompositionWhole.class)) {
                System.out.println(compositionWhole);
                if (compositionWhole.partTarget().equals(partClass)) {
                    return true;
                }
            }

        }

        return false;

    }

    public static <T extends ExtensionAnnotationAssociationManager> boolean addPart(
            T whole,
            T part
    ) throws Exception {

        Class<? extends ExtensionAnnotationAssociationManager> wholeClass = whole.getClass();
        Class<? extends ExtensionAnnotationAssociationManager> partClass = part.getClass();

        String wholeRoleName = null;
        String partRoleName = null;

        boolean assumeTheWorst = true;

        // whole must have the proper OneToManyAssociation annotation type if not there is an error
        if (wholeClass.getAnnotationsByType(OneToManyAssociation.class).length > 0) {

            for (OneToManyAssociation wholeClassAnnotation : wholeClass.getAnnotationsByType(OneToManyAssociation.class)) {

                if (wholeClassAnnotation.target().equals(partClass)) {
                    wholeRoleName = wholeClassAnnotation.role();
                    assumeTheWorst = false;
                    break;
                }

            }
        }

        if (assumeTheWorst) {
            throw new Exception(String.format(
                    "There is no appropriate OneToManyAssociation between %s and %s",
                    wholeClass.getSimpleName(),
                    partClass.getSimpleName()));
        }

        // part must have the proper ManyToOneAssociation annotation type if not there is an error
        assumeTheWorst = true;

        if (partClass.getAnnotationsByType(ManyToOneAssociation.class).length > 0) {

            Annotation[] partClassAnnotations = partClass.getAnnotations();
            for (ManyToOneAssociation partClassAnnotation : partClass.getAnnotationsByType(ManyToOneAssociation.class)) {

                if (partClassAnnotation.target().equals(wholeClass)) {
                    partRoleName = partClassAnnotation.role();
                    assumeTheWorst = false;
                    break;
                }

            }
        }

        if (assumeTheWorst) {
            throw new Exception(String.format(
                    "There is no appropriate ManyToOneAssociation between %s and %s",
                    wholeClass.getSimpleName(),
                    partClass.getSimpleName()));
        }


        System.out.println(wholeRoleName);
        System.out.println(partRoleName);

        // now we have roles
        whole.addPart(wholeRoleName, partRoleName, part);


        System.out.println("Created Composition between " + whole + "  " + part);

        return true;
    }

    /**
     * makes many to many association with middleClass technique
     * <p>
     * returns Association class if the middleClass is marked as one
     *
     * @param targetObject
     * @param <T>
     * @return
     */

    public <T extends ExtensionAnnotationAssociationManager> ExtensionAnnotationAssociationManager
    addManyToManyLinkWithAttributeClass(
            T targetObject
    ) throws Exception {

        return addManyToManyLink(targetObject);
//
//        this.printRoles();
//
//        targetObject.printRoles();
//
//        // get association class created between THIS object and targetObject
//        ExtensionAnnotationAssociationManager associationAttributeClass = getAssociationAttributeClass(
//                targetObject
//        );
//
//        System.out.println(associationAttributeClass);
//
//
//        return associationAttributeClass;
    }

    /**
     * Adds ManyToMany association between THIS object and targetObject if possible
     * Throws exception otherwise
     *
     * @param <T>
     * @param targetObject
     * @return
     */

    public <T extends ExtensionAnnotationAssociationManager> ExtensionAnnotationAssociationManager addManyToManyLink(
            T targetObject
    ) throws Exception {
        // check if THIS and target class has the same ManyToMany association class type
        // because there can be multiple association types on the same class declared
        // you must check when one association type is declared
        // or many of the same type
//        if (!(
//                (this.getClass().isAnnotationPresent(ManyToManyAssociation.class) ||
//                        this.getClass().isAnnotationPresent(ManyToManyAssociations.class)
//                )
//                        &&
//                        (targetObject.getClass().isAnnotationPresent(ManyToManyAssociation.class) |
//                                targetObject.getClass().isAnnotationPresent(ManyToManyAssociations.class)
//                        ))) {
        if (!(this.getClass().getAnnotationsByType(ManyToManyAssociation.class).length > 0 &&
                targetObject.getClass().getAnnotationsByType(ManyToManyAssociation.class).length > 0)) {
            throw new Exception(String.format("Cannot link %s <-> %s in manyToMany association",
                    this.getClass().getSimpleName(),
                    targetObject.getClass().getSimpleName()));
        }

        // check whether the objects can have this association type (Many To Many)
        // whether do they have the same attribute class
//        ArrayList<Annotation[]> associatedAnnotationsFromSourceAndTarget =
//                getAssociatedAnnotationsFromSourceAndTarget(this.getClass(), targetObject.getClass());
        ArrayList<Annotation[]> associatedAnnotationsFromSourceAndTarget =
                getAssociationsByGivenType(
                        this.getClass(),
                        targetObject.getClass(),
                        ManyToManyAssociation.class
                );

        // TODO:SL WARING! - getAssociatedAnnotationsFromSourceAndTarget for now assumes that One class can have one association of each type max
        // TODO:SL this method however will assume (for future) that One class can have many association of each type
        System.out.println("#############################################################");
        boolean manyToManyLinkCreated = false;
        for (Annotation[] annotations : associatedAnnotationsFromSourceAndTarget) {

            System.out.println(annotations[0]);
            System.out.println(annotations[1]);
            System.out.println("--");

            // check if this is a valid association pair
            if (annotations.length != 2) continue;


            // check if pairs are many to many
//            System.out.println(annotations[0].annotationType());
//            System.out.println(ManyToManyAssociation.class);
//            System.out.println(ManyToManyAssociation.class.equals(annotations[0].annotationType()));
//            System.out.println(ManyToManyAssociation.class.equals(annotations[1].annotationType()));
            if (!(
                    ManyToManyAssociation.class.equals(annotations[0].annotationType()) &&
                            ManyToManyAssociation.class.equals(annotations[1].annotationType())
            )) {
                continue;
            }

            // both annotations[0] and annotations[1] are ManyToMany so cast them
            ManyToManyAssociation thisManyToManyAssociation = (ManyToManyAssociation) annotations[0];
            ManyToManyAssociation targetManyToManyAssociation = (ManyToManyAssociation) annotations[1];

            // check if annotation pair have the same attribute class
//            System.out.println(annotations[0].annotationType().getMethod("middleClass").invoke(annotations[0]));
//            System.out.println(annotations[1].annotationType().getMethod("middleClass").invoke(annotations[1]));
//            System.out.println(annotations[0].annotationType().getMethod("middleClass").invoke(annotations[0]).equals(
//                    annotations[1].annotationType().getMethod("middleClass").invoke(annotations[1])
//            ));
            if (!(thisManyToManyAssociation.annotationType().getMethod("middleClass").invoke(annotations[0]).equals(
                    targetManyToManyAssociation.annotationType().getMethod("middleClass").invoke(annotations[1])
            )
            )
            ) continue;
            ;

            System.out.println("Lets GOOOOO");

            // create this instance of this association class
            // determining which is target1 of association class (THIS or target)

            // get the middleCLass type
            Class<?> middleClass = (Class<?>) targetManyToManyAssociation.annotationType().getMethod("middleClass").invoke(targetManyToManyAssociation);
//            System.out.println(middleClass);
//            System.out.println(middleClass.getConstructor(
//                    new Class[]{
//                            middleClass.getAnnotation(AttributeClass.class).target1(),
//                            middleClass.getAnnotation(AttributeClass.class).target2()
//                    })
//
//            );

            // get the constructor of that class
            Constructor middleClassConstructor = middleClass.getConstructor(
                    new Class[]{
                            middleClass.getAnnotation(AssociationClass.class).target1(),
                            middleClass.getAnnotation(AssociationClass.class).target2()
                    }
            );

            // create middleObject
            System.out.println("THIS : " + this.getClass().getSimpleName());
            System.out.println("target : " + targetObject.getClass().getSimpleName());

            ExtensionAnnotationAssociationManager middleObject =
                    (ExtensionAnnotationAssociationManager) middleClassConstructor.newInstance(
                            (this.getClass().equals(middleClass.getAnnotation(AssociationClass.class).target1()) ? this : targetObject),
                            (this.getClass().equals(middleClass.getAnnotation(AssociationClass.class).target2()) ? this : targetObject)
                    );

            System.out.println(middleObject);

            // get middleMovie annotation - we know it must be AttributeClass
            AssociationClass middleAttributeClassAnnotation = middleClass.getAnnotation(AssociationClass.class);

            // add links
            // if THIS is AttributeClass's target1 class object then create link with role1 between THIS and middleObject
            // if not use role 2
            if (this.getClass().equals(middleClass.getAnnotation(AssociationClass.class).target1())) {
                System.out.println(this.getClass().getSimpleName() + " role1 : " + middleClass.getAnnotation(AssociationClass.class).role1());
                this.addLink(
                        thisManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role1(),
                        middleObject);
            } else {
                System.out.println(this.getClass().getSimpleName() + " role2 : " + middleClass.getAnnotation(AssociationClass.class).role2());
                this.addLink(
                        thisManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role2(),
                        middleObject);
            }

            // if targetObject is AttributeClass's target1 class object then create link with role1 between targetObject and middleObject
            // if not use role 2
            if (targetObject.getClass().equals(middleClass.getAnnotation(AssociationClass.class).target1())) {
                System.out.println(targetObject.getClass().getSimpleName() + " role1 : " + middleClass.getAnnotation(AssociationClass.class).role1());
                targetObject.addLink(
                        targetManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role1(),
                        middleObject);
            } else {
                System.out.println(targetObject.getClass().getSimpleName() + " role2 : " + middleClass.getAnnotation(AssociationClass.class).role2());
                targetObject.addLink(
                        targetManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role2(),
                        middleObject);
            }


//            manyToManyLinkCreated = true;


            return middleObject;
            // for the second assume the latter (the THIS or target whatever is not set to be target1)
            // determine if THIS class and targetClass objects are appriopiate type given in AssociationT

            // if so add links between appropriate classes
            // create association class


            // add link between association class and THIS and Target class


        }


        // if objects cannot have many to many association type throw exception (after the for we know that it isnt)
        if (!manyToManyLinkCreated)
            throw new Exception(String.format("Cannot link %s and %s in many to many association",
                    this.getClass().getSimpleName(),
                    targetObject.getClass().getSimpleName()));


        return null;
    }

    private ArrayList<Annotation[]> getAssociationsByGivenType(
            Class<? extends ExtensionAnnotationAssociationManager> source,
            Class<? extends ExtensionAnnotationAssociationManager> target,
            Class<ManyToManyAssociation> associationTypeAnnotation) throws Exception {

        System.out.println(source.getAnnotationsByType(associationTypeAnnotation).length);
        System.out.println(target.getAnnotationsByType(associationTypeAnnotation).length);

        if (!(source.getAnnotationsByType(associationTypeAnnotation).length > 0
                && target.getAnnotationsByType(associationTypeAnnotation).length > 0)) {
            throw new Exception(String.format("there is no association between %s and %s for a given Association Type %s",
                    source.getSimpleName(), target.getSimpleName(), associationTypeAnnotation.getSimpleName()));
        }

        ArrayList<Annotation[]> associationsByGivenType = new ArrayList<>();
        System.out.println(associationTypeAnnotation.getSimpleName().equals(ManyToManyAssociation.class.getSimpleName()));

        // loop through all annotations of a given type on source
        // and compare in loop with all annotations with a given type from Target
        System.out.println("--");
        for (Annotation sourceAnnotationByGivenType : source.getAnnotationsByType(associationTypeAnnotation)) {
            System.out.println(sourceAnnotationByGivenType);
            for (Annotation targetAnnotationByGivenType : target.getAnnotationsByType(associationTypeAnnotation)) {

                // now there are many possible options
                // we know that sourceAnnotationsByGivenType and targetAnnotationsByGivenType
                // are
                // the same Type
                // all we need to do is to check the if the association point at eachothers target
                // if so it means they are related
                // we add them if they are related to the associationsByGivenType array list as a pair
                // of annotations sourceAnnotation targetAnnotation
                System.out.println(targetAnnotationByGivenType);
                System.out.println(
                        target.equals(
                                sourceAnnotationByGivenType.getClass()
                                        .getMethod("target").invoke(sourceAnnotationByGivenType)
                        )
                );
                System.out.println(
                        source.equals(
                                targetAnnotationByGivenType.getClass()
                                        .getMethod("target").invoke(targetAnnotationByGivenType)
                        )
                );
                if (
                        target.equals(
                                sourceAnnotationByGivenType.getClass()
                                        .getMethod("target").invoke(sourceAnnotationByGivenType)
                        ) && source.equals(
                                targetAnnotationByGivenType.getClass()
                                        .getMethod("target").invoke(targetAnnotationByGivenType)
                        )


                ) {
                    associationsByGivenType.add(new Annotation[]{
                            sourceAnnotationByGivenType, targetAnnotationByGivenType
                    });
                }


            }
            System.out.println("--");
        }

        System.out.println("))");
        for (Annotation[] associations : associationsByGivenType) {
            System.out.println(associations[0]);
            System.out.println(associations[1]);
        }

        return associationsByGivenType;
    }

    public <T extends ExtensionAnnotationAssociationManager> ExtensionAnnotationAssociationManager getAssociationAttributeClass(
            T target) throws Exception {

        if (!target.getClass().isAnnotationPresent(ManyToManyAssociation.class)) {
            throw new Exception(String.format("In %s there in no required ManyToManyAssociationAnnotation",
                    target.getClass().getSimpleName()));
        }
        ExtensionAssociationManager[] attributeClasses = getLinks(
                this.getClass().getAnnotation(ManyToManyAssociation.class).role()
        );

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // check if there are any association attributes
        if (attributeClasses.length > 0) {

            for (ExtensionAssociationManager attributeClass : attributeClasses) {


                // check whether it is association attribute class and it has association class annotation present
                if (!attributeClass.getClass().isAnnotationPresent(AttributeClass.class) ||
                        !attributeClass.getClass().isAnnotationPresent(AssociationClass.class)) {

                    // if not something gone wrong . Log this and move on
                    continue;
                }


                AssociationClass associationClassAnnotation = attributeClass.getClass().getAnnotation(AssociationClass.class);

                // check if any target values of associationClass is the same as target class type
                if (associationClassAnnotation.target1().equals(target.getClass()) ||
                        associationClassAnnotation.target2().equals(target.getClass())) {
                    System.out.println("YEP IT HAS!!!");

                    // if so return this association class
                    return (ExtensionAnnotationAssociationManager) attributeClass;
                }

            }
        }

        throw new Exception(String.format("There is no association attribute classs available between " +
                        "these to objects : \n%s\n%s for this %s role name",
                this,
                target,
                target.getClass().getAnnotation(ManyToManyAssociation.class).role()));


    }

    @Override
    public void showLinks(String roleName, PrintStream stream) throws Exception {

        ExtensionAssociationManager[] links = getLinks(roleName);

        System.out.println("_____________________________________");

        // print whole manyTo Many association


        System.out.printf("%s \n   %s:\n",
                this,
                roleName);
        for (ExtensionAssociationManager target : links) {

//            System.out.println(target);
            if (target.getClass().isAnnotationPresent(AssociationClass.class)) {

                // if with attribute class print attributes for this associations as well
                boolean attributeClassFlag = target.getClass().isAnnotationPresent(AttributeClass.class);


//                System.out.println("WW");

                // show the real target
                // get assocation annotation from target link
                // if target1 for this annotation is THIS class than show target2 if not the opposite
                // AT THIS POINT THERE COULD BE ONE THIS KIND OF ANNOTATION OF THIS TYPE
                AssociationClass associationClassAnnotation = target.getClass().getAnnotation(AssociationClass.class);

                if (attributeClassFlag) {
                    System.out.print("   association attributes { ");
                    for (Method method : target.getClass().getMethods()) {
                        if (method.isAnnotationPresent(Attribute.class)) {
                            method.setAccessible(true);
                            System.out.print(method.invoke(target) + " ");
                        }
                    }
                    System.out.print("} ");
                }

                if (associationClassAnnotation.target1().equals(this.getClass())) {
                    for (Method method : target.getClass().getMethods()) {
                        if (method.isAnnotationPresent(Target2Getter.class)) {
//                            System.out.println("present  Target2Getter :)");
                            method.setAccessible(true);
                            System.out.print("   " + method.invoke(target));
                        }
                    }
                } else {
                    for (Method method : target.getClass().getMethods()) {
                        if (method.isAnnotationPresent(Target1Getter.class)) {
//                            System.out.println("present  Target1Getter :)");
                            method.setAccessible(true);
                            System.out.print("   " + method.invoke(target));
                        }
                    }
                }


                System.out.println();


            } else {
                System.out.println("   " + target);
            }

        }
    }

    /**
     * creates a many to one association with attribute from the caller (THIS) perspective.
     *
     * @param target
     */
    public <T extends ExtensionAnnotationAssociationManager> void addManyToOneAssociationWithAttribute(T target) throws Exception {

        // check if manyTo one exists in the caller (THIS) (Many To one wih target set to target)
        if (this.getClass().isAnnotationPresent(ManyToOneAssociation.class)) {
            ManyToOneAssociation sourceQualifiedAssociationAnnotation = this.getClass().getAnnotation(ManyToOneAssociation.class);
            if (sourceQualifiedAssociationAnnotation.target().equals(target.getClass())) {

                // check if the exists correspond on the target side (Many to One with qualfied flag on with target set to caller)
                if (target.getClass().isAnnotationPresent(OneToManyAssociation.class)) {
                    OneToManyAssociation targetQualifiedAssociationAnnotation = target.getClass().getAnnotation(OneToManyAssociation.class);

                    if (targetQualifiedAssociationAnnotation.qualified() && targetQualifiedAssociationAnnotation.target().equals(this.getClass())) {
                        // we are good to go

                        // add link with qualifier between classes

                        // get roles
                        String sourceRoleName = sourceQualifiedAssociationAnnotation.role();
                        String targetRoleName = targetQualifiedAssociationAnnotation.role();

                        ifPossibleMakeQualifiedAssociationBetweenObjects(
                                this,
                                target,
                                sourceQualifiedAssociationAnnotation,
                                targetQualifiedAssociationAnnotation,
                                sourceRoleName,
                                targetRoleName);


                    }
                }
            }
        } else {
            throw new Exception(String.format("Unable to create Qualified Association between %s and %s",
                    this.getClass().getSimpleName(),
                    target.getClass().getSimpleName()));
        }


    }

    public <T extends ExtensionAnnotationAssociationManager> void addManyToOneLink(T target) throws Exception {

        boolean associationAdded = false;

        // check if the target is a One side
        if (target.getClass().isAnnotationPresent(OneToManyAssociation.class)) {
            OneToManyAssociation targetOneToManyAssociationAnnotation = target.getClass().getAnnotation(OneToManyAssociation.class);
            // if so check if the exists between the two
            if (targetOneToManyAssociationAnnotation.target().equals(this.getClass())) {
                // its ok
                // if so add link
                addOneToManyLink(target, this);
                associationAdded = true;
            }
        }

        if (!associationAdded) {
            throw new Exception(String.format("Cannot create ManyToOne Association between %s and %s",
                    this.getClass().getSimpleName(),
                    target.getClass().getSimpleName()));
        }
    }


    public <T extends ExtensionAnnotationAssociationManager> void addOneToManyLink(T target) throws Exception {

        boolean associationAdded = false;

        // check if the caller is a One side
        if (this.getClass().isAnnotationPresent(OneToManyAssociation.class)) {
            System.out.println("WW");
            OneToManyAssociation callerOneToManyAssociationAnnotation = this.getClass().getAnnotation(OneToManyAssociation.class);
            // if so check if the exists between the two
            if (callerOneToManyAssociationAnnotation.target().equals(target.getClass())) {
                System.out.println("WWWW");
                // its ok
                // if so add link
                addOneToManyLink(this, target);
                associationAdded = true;
            }
        }

        if (!associationAdded) {
            throw new Exception(String.format("Cannot create OneToMany Association between %s and %s",
                    this.getClass().getSimpleName(),
                    target.getClass().getSimpleName()));
        }

    }

    private <S extends ExtensionAnnotationAssociationManager, T extends ExtensionAnnotationAssociationManager>
    void addOneToManyLink(S source, T target) throws Exception {

        OneToManyAssociation callerOneToManyAssociationAnnotation = source.getClass().getAnnotation(OneToManyAssociation.class);

        // check if the target has OneToMany association annotation valid
        if (target.getClass().isAnnotationPresent(ManyToOneAssociation.class)) {
            ManyToOneAssociation targetManyToOneAssociationAnnotation = target.getClass().getAnnotation(ManyToOneAssociation.class);
            if (targetManyToOneAssociationAnnotation.target().equals(source.getClass())) {
                // TODO:SL what to do when OneToMany on the one side exists already? now we throw exception

                if (target.hasRole(targetManyToOneAssociationAnnotation.role())) {
                    throw new Exception(String.format("Sorry for now we do not support for changing current ONE side in OneToMany association. Becouse already OneToMany Association exists between %s and %s associaotion new cannot be created",
                            source.getClass().getSimpleName(),
                            target.getClass().getSimpleName()));
                }

                // uyes it is ok
                // add link :)
                String sourceRoleName = callerOneToManyAssociationAnnotation.role();
                String targetRoleName = targetManyToOneAssociationAnnotation.role();

                source.addLink(sourceRoleName, targetRoleName, target);
            }
        }

    }

    protected <S extends ExtensionAnnotationAssociationManager, A extends ExtensionAnnotationAssociationManager>
    boolean isValidAttributeClass(
            S source,
            A attributeClass
    ) {
        // check if source has ManyToMany association -> otherwise return false;
        if (!(source.getClass().getAnnotationsByType(ManyToManyAssociation.class).length > 0)) {
            return false;
        }

//        // get ManyToMany Association annotation from source
//        ManyToManyAssociation sourceManyToManyAssociationAnnotation =
//                source.getClass().getAnnotation(ManyToManyAssociation.class);
//
//        // check the middleClass from this annotation against attributeClass type -> if they match return true otherwise return false
//        if (sourceManyToManyAssociationAnnotation.middleClass().equals(attributeClass.getClass())) {
//            return true;
//        } else {
//            return false;
//        }

        // check if middleClass of ManyToManyAssociation points to the attributeClass type
        // -> if any return true
        // -> if none return false
        for (ManyToManyAssociation sourceManyToManyAssociationAnnotation : source.getClass().getAnnotationsByType(ManyToManyAssociation.class)) {

            if(sourceManyToManyAssociationAnnotation.middleClass().equals(attributeClass.getClass())){
                return true;
            }
        }
        return false;

    }


}
