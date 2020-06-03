package com.slawomirlasik.diet_plan_management.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

// TODO:SL implement association with attribute
// TODO:SL implement association with qualifier
// TODO:SL handle many TO Many Associations

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

        addLink(sourceRoleName, targetRoleName, target);


    }

    // TODO:SL Implement cardinalityAreMet method
    private boolean cardinalityAreMet(Annotation[] annotations) {

        return true;
    }

    private ArrayList<Annotation[]> getAssociatedAnnotationsFromSourceAndTarget(
            Class<? extends ExtensionAnnotationAssociationManager> sourceClass,
            Class<? extends ExtensionAnnotationAssociationManager> targetClass) {

        ArrayList<Annotation[]> correspondedAnnotationPairs = new ArrayList<>();

        System.out.println(sourceClass);
        System.out.println(targetClass);

        for (Annotation sourceAnnotation : sourceClass.getAnnotations()) {
            System.out.println(sourceAnnotation.annotationType().getSimpleName());
            switch (sourceAnnotation.annotationType().getSimpleName()) {
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
        if (partClass.equals(wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget())
                &&
                wholeObject.getClass().getAnnotation(CompositionWhole.class).partTarget().equals(partClass)) {

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
        whole.addPart(wholeRoleName,  partRoleName, part);


        return true;
    }


}
