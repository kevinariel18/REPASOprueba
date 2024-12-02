import React from 'react';
import { View, StyleSheet } from 'react-native';
import { Button, Text } from '@rneui/base'; 
import { getAllPostsService, createPostService, updatePostService, getByUserIdService, getProductsService, postChuckNorrisJoke, updateProductService} from '../services/TestServices'; // Servicio externo

export const TestWebServices = () => {
  
  const getAllPosts = () => {
    getAllPostsService(); 
  };

  const createPost = () => {
    createPostService(); 
  };


const updatePost = () => {
  updatePostService();
};


const getByUserId = () => {
  getByUserIdService();
};


const getProducts = () => {
  getProductsService();
};


const createJoke = () => {
  postChuckNorrisJoke();
};


const updateProduct = () => {
  updateProductService();
};





  return (
    <View style={styles.container}>
      <Text style={styles.textContainer}>MODULO 3</Text>
      <View style={styles.buttonContainer}>
        <Button
          title="Recuperar Posts"
          onPress={getAllPosts} 
          buttonStyle={styles.button}
        />
        <Button
          title="Crear Post"
          onPress={createPost} 
          buttonStyle={styles.button}
        />
        <Button
          title="Actualizar Post"
          onPress={updatePost}
          buttonStyle={styles.button}
        />
        <Button
          title="Filtrar"
          onPress={getByUserId}
          buttonStyle={styles.button}
        />

<Button
          title="XXX"
          onPress={getProducts}
          buttonStyle={styles.button}
        />
        <Button
          title="YYY"
          onPress={createJoke}
          buttonStyle={styles.button}
        />
        <Button
          title="ZZZ"
          onPress={updateProduct}
          buttonStyle={styles.button}
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
  },
  textContainer: {
    flex: 1,
    textAlign: 'center',
    fontSize: 18,
    fontWeight: 'bold',
    marginVertical: 20,
  },
  buttonContainer: {
    flex: 6,
    justifyContent: 'space-around',
    marginHorizontal: 10,
  },
  button: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
  },
});
