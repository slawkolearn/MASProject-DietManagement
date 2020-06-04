package com.slawomirlasik.diet_plan_management.util;

import com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany.Actor;
import com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany.Movie;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

// TODO:SL implement association with attribute
// TODO:SL handle many TO Many Associations
// TODO:SL check whether multiple association are handled properly
// TODO:SL separate adding association by type -> one associationType one method
// TODO:SL add method that add all possible associations between given two objects

public class ExtensionAnnotationAssociationManager extends ExtensionAssociationManager
        implements Serializable {

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

    public <T extends ExtensionAnnotationAssociationManager> void addLink(
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
                case "ManyToManyAssociationWithAttribute":
                    ManyToManyAssociationWithAttribute sourceManyToManyAssociationWithAttributeAnnotation =
                            (ManyToManyAssociationWithAttribute) sourceAnnotation;
                    if (targetClass.isAnnotationPresent(ManyToManyAssociationWithAttribute.class)) {
                        System.out.println("Yes it is ManyToManyAssociationWithAttribute present");
                        ManyToManyAssociationWithAttribute targetManyToManyAssociationWithAttributeAnnotation =
                                targetClass.getAnnotation(ManyToManyAssociationWithAttribute.class);

                        System.out.println(sourceManyToManyAssociationWithAttributeAnnotation);
                        System.out.println(targetManyToManyAssociationWithAttributeAnnotation);


                        // check if those annoations concerns each other

                        if (
                                sourceManyToManyAssociationWithAttributeAnnotation.target().equals(targetClass) &&
                                        targetManyToManyAssociationWithAttributeAnnotation.target().equals(sourceClass)
                        ) {
                            System.out.println("added");

                            correspondedAnnotationPairs.add(new Annotation[]{
                                    sourceManyToManyAssociationWithAttributeAnnotation,
                                    targetManyToManyAssociationWithAttributeAnnotation
                            });
                        }

                    }
                    break;
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

        // check if whole is appriopiate class type
        if (
                wholeObject.getClass().isAnnotationPresent(CompositionWhole.class) &&
                        partClass.equals(wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget()) &&
                        wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget().equals(partClass)
        ) {

            return true;
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
        if (wholeClass.isAnnotationPresent(OneToManyAssociation.class)) {

            Annotation[] wholeClassAnnotations = wholeClass.getAnnotations();
            for (Annotation wholeClassAnnotation : wholeClassAnnotations) {

                if (wholeClassAnnotation.annotationType().equals(OneToManyAssociation.class)
                        && ((OneToManyAssociation) wholeClassAnnotation).target().equals(partClass)) {
                    wholeRoleName = ((OneToManyAssociation) wholeClassAnnotation).role();
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

        if (partClass.isAnnotationPresent(ManyToOneAssociation.class)) {

            Annotation[] partClassAnnotations = partClass.getAnnotations();
            for (Annotation partClassAnnotation : partClassAnnotations) {

                if (partClassAnnotation.annotationType().equals(ManyToOneAssociation.class)
                        && ((ManyToOneAssociation) partClassAnnotation).target().equals(wholeClass)) {
                    partRoleName = ((ManyToOneAssociation) partClassAnnotation).role();
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
     * Adds ManyToMany association between THIS object and targetObject if possible
     * Throws exception otherwise
     *
     * @param targetObject
     * @param <T>
     */

    public <T extends ExtensionAnnotationAssociationManager> void addManyToManyLink(
            T targetObject
    ) throws Exception {
        // check if THIS and target class has the same ManyToMany association class type
        if (!(this.getClass().isAnnotationPresent(ManyToManyAssociation.class) &&
                targetObject.getClass().isAnnotationPresent(ManyToManyAssociation.class))) {
            throw new Exception(String.format("Cannot link %s <-> %s in manyToMany association",
                    this.getClass().getSimpleName(),
                    targetObject.getClass().getSimpleName()));
        }

        // check whether the objects can have this association type (Many To Many)
        // whether do they have the same attribute class
        ArrayList<Annotation[]> associatedAnnotationsFromSourceAndTarget =
                getAssociatedAnnotationsFromSourceAndTarget(this.getClass(), targetObject.getClass());

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
                            middleClass.getAnnotation(AttributeClass.class).target1(),
                            middleClass.getAnnotation(AttributeClass.class).target2()
                    }
            );

            // create middleObject
            ExtensionAnnotationAssociationManager middleObject =
                    (ExtensionAnnotationAssociationManager) middleClassConstructor.newInstance(
                            (this.getClass().equals(Actor.class) ? this : targetObject ),
                            (this.getClass().equals(Movie.class) ? this : targetObject )
                    );

            System.out.println(middleObject);

            // get middleMovie annotation - we know it must be AttributeClass
            AttributeClass middleAttributeClassAnnotation = middleClass.getAnnotation(AttributeClass.class);

            // add links
            // if THIS is AttributeClass's target1 class object then create link with role1 between THIS and middleObject
            // if not use role 2
            if( this.getClass().equals(middleClass.getAnnotation(AttributeClass.class).target1())){
                System.out.println(this.getClass().getSimpleName() + " role1 : " + middleClass.getAnnotation(AttributeClass.class).role1());
                this.addLink(
                        thisManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role1(),
                        middleObject);
            }else{
                System.out.println(this.getClass().getSimpleName() + " role2 : "  + middleClass.getAnnotation(AttributeClass.class).role2());
                this.addLink(
                        thisManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role2(),
                        middleObject);
            }

            // if targetObject is AttributeClass's target1 class object then create link with role1 between targetObject and middleObject
            // if not use role 2
            if( targetObject.getClass().equals(middleClass.getAnnotation(AttributeClass.class).target1())){
                System.out.println(targetObject.getClass().getSimpleName() + " role1 : " + middleClass.getAnnotation(AttributeClass.class).role1());
                targetObject.addLink(
                        targetManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role1(),
                        middleObject);
            }else{
                System.out.println(targetObject.getClass().getSimpleName() + " role2 : "  + middleClass.getAnnotation(AttributeClass.class).role2());
                targetObject.addLink(
                        targetManyToManyAssociation.role(),
                        middleAttributeClassAnnotation.role2(),
                        middleObject);
            }


            manyToManyLinkCreated = true;

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


    }
}
