import { View, StyleSheet, Alert } from 'react-native';
import { Button, Input, Text } from '@rneui/base';
import { useState } from 'react';
import { createPostServicee, getDocumentTypes, createDocumentTypeService } from '../services/TestServices';

export const PostForm = () => {
    const [subject, setSubject] = useState('');
    const [message, setMessage] = useState('');
    const [documentTypeName, setDocumentTypeName] = useState(''); 

    // Crear un post
    const createPost = () => {
        if (!subject || !message) {
            Alert.alert("Error", "Ambos campos son obligatorios.");
            return;
        }

        console.log("Creando post con los valores ingresados:");
        console.log("Título:", subject);
        console.log("Mensaje:", message);

        createPostServicee(
            {
                title: subject,
                body: message,
            },
            () => {
                Alert.alert("CONFIRMACIÓN", "SE HA INGRESADO UN NUEVO POST");
            }
        )
        .catch(error => {
            Alert.alert("ERROR", "No se pudo crear el post. Intente de nuevo.");
        });
    };

    // Obtener los tipos de documentos
    const fetchDocumentTypes = async () => {
        try {
            const documentTypes = await getDocumentTypes();
            console.log("Tipos de documentos obtenidos:", documentTypes);
            Alert.alert("Éxito", "Tipos de documentos cargados. Revisa la consola.");
        } catch (error) {
            console.error("Error al obtener los tipos de documentos:", error);
            Alert.alert("Error", "No se pudo cargar los tipos de documentos.");
        }
    };

    // Crear un nuevo tipo de documento
    const createDocumentType = () => {
        if (!documentTypeName) {
            Alert.alert("Error", "El nombre del tipo de documento es obligatorio.");
            return;
        }

        console.log("Creando tipo de documento con el nombre:", documentTypeName);

        createDocumentTypeService(
            { name: documentTypeName },
            () => {
                Alert.alert("CONFIRMACIÓN", "SE HA INGRESADO UN NUEVO TIPO DE DOCUMENTO");
                setDocumentTypeName(''); // Limpiar el campo después de la creación
            }
        )
        .catch(error => {
            Alert.alert("ERROR", "No se pudo crear el tipo de documento. Intente de nuevo.");
        });
    };

    return (
        <View style={styles.container}>
            <View style={styles.textContainer}>
                <Text h4>NUEVO MENSAJE</Text>
            </View>
            <View style={styles.formContainer}>
                <Input
                    placeholder="TITULO"
                    value={subject}
                    onChangeText={setSubject}
                />
                <Input
                    placeholder="MENSAJE"
                    value={message}
                    onChangeText={setMessage}
                />
                <Button
                    title="Guardar"
                    onPress={createPost}
                />

                <Button
                    title="TIPOS DE DOCUMENTOS"
                    onPress={fetchDocumentTypes}
                    containerStyle={styles.button}
                />
                <Input
                    placeholder="NOMBRE TIPO DE DOCUMENTO"
                    value={documentTypeName}
                    onChangeText={setDocumentTypeName}
                />
                <Button
                    title="Guardar Tipo de Documento"
                    onPress={createDocumentType}
                    containerStyle={styles.button}
                />
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: '#fff',
        justifyContent: 'center',
    },
    textContainer: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginVertical: 10,
    },
    formContainer: {
        flex: 7,
        flexDirection: 'column',
        justifyContent: 'center',
    },
    button: {
        marginTop: 10,
    },
});
