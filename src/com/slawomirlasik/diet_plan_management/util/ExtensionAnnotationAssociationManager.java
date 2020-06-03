package com.slawomirlasik.diet_plan_management.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;

public class ExtensionAnnotationAssociationManager extends ExtensionAssociationManager
        implements Serializable {

    public ExtensionAnnotationAssociationManager() {
        super();
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

        if(associatedAnnotationsFromSourceAndTarget.size() > 1){
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
        if(!cardinalityAreMet(associatedAnnotationsFromSourceAndTarget.get(0))){
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
                    if ( targetClass.isAnnotationPresent(ManyToManyAssociation.class)){
                        System.out.println("Yes it is ManyToManyAssociation present");
                        ManyToManyAssociation targetManyToManyAssociationAnnotation = targetClass.getAnnotation(ManyToManyAssociation.class);

                        System.out.println(sourceManyToManyAssociationAnnotation);
                        System.out.println(targetManyToManyAssociationAnnotation);

                        // check if those annoations concerns each other

                        if(
                                sourceManyToManyAssociationAnnotation.target().equals(targetClass) &&
                                        targetManyToManyAssociationAnnotation.target().equals(sourceClass)
                        ){
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
                    if ( targetClass.isAnnotationPresent(OneToManyAssociation.class)){
                        System.out.println("Yes it is OneToManyAssociation present");
                        OneToManyAssociation targetOneToManyAssociationAnnotation = targetClass.getAnnotation(OneToManyAssociation.class);

                        System.out.println(sourceManyToOneAssociationAnnotation);
                        System.out.println(targetOneToManyAssociationAnnotation);

                        // check if those annoations concerns each other

                        if(
                                sourceManyToOneAssociationAnnotation.target().equals(targetClass) &&
                                        targetOneToManyAssociationAnnotation.target().equals(sourceClass)
                        ){
                            // if so add the pair for returning
                            correspondedAnnotationPairs.add(new Annotation[] {
                                    sourceManyToOneAssociationAnnotation,
                                    targetOneToManyAssociationAnnotation
                            });
                        }

                    }
                    break;
                case "OneToManyAssociation":
                    OneToManyAssociation sourceOneToManyAssociationAnnotation = (OneToManyAssociation) sourceAnnotation;
                    if ( targetClass.isAnnotationPresent(ManyToOneAssociation.class)){
                        System.out.println("Yes it is ManyToOneAssociation present");
                        ManyToOneAssociation targetManyToOneAssociationAnnotation = targetClass.getAnnotation(ManyToOneAssociation.class);

                        System.out.println(sourceOneToManyAssociationAnnotation);
                        System.out.println(targetManyToOneAssociationAnnotation);


                        // check if those annoations concerns each other

                        if(
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
                    if ( targetClass.isAnnotationPresent(ManyToManyAssociationWithAttribute.class)){
                        System.out.println("Yes it is ManyToManyAssociationWithAttribute present");
                        ManyToManyAssociationWithAttribute targetManyToManyAssociationWithAttributeAnnotation =
                                targetClass.getAnnotation(ManyToManyAssociationWithAttribute.class);

                        System.out.println(sourceManyToManyAssociationWithAttributeAnnotation);
                        System.out.println(targetManyToManyAssociationWithAttributeAnnotation);


                        // check if those annoations concerns each other

                        if(
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


}
