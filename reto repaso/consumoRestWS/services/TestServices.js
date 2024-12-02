export const getAllPostsService = () => {
    console.log("Intentando recuperar los posts...");
    fetch('https://jsonplaceholder.typicode.com/posts') 
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json();
      })
      .then((json) => {
        console.log("Datos recibidos:", json); 
      })
      .catch((error) => {
        console.error("Error al recuperar los posts:", error); 
      });
  };
  


  export const createPostService = () => {
    
    const config = {
      method: 'POST',
      body: JSON.stringify({
        title: 'mensaje',
        body: 'suerte en su evaluación',
        userId: 1,
      }),
      headers: {
        'Content-Type': 'application/json', 
      },
    };
  
    
    fetch('https://jsonplaceholder.typicode.com/posts', config)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Post creado:', json); 
      })
      .catch((error) => {
        console.error('Error al crear el post:', error); 
      });
  };
  

  export const updatePostService = () => {
    
    const config = {
      method: 'PUT',
      body: JSON.stringify({
        id: 1,
        title: 'mensaje final',
        body: 'hasta la vista baby',
        userId: 1,
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    };
  
    
    fetch('https://jsonplaceholder.typicode.com/posts/1', config)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Post actualizado:', json); 
      })
      .catch((error) => {
        console.error('Error al actualizar el post:', error); 
      });
  };
  





  export const getByUserIdService = () => {
    
    fetch('https://jsonplaceholder.typicode.com/posts?userId=1')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Posts filtrados por userId:', json); 
      })
      .catch((error) => {
        console.error('Error al filtrar posts:', error); 
      });
  };


  export const getProductsService = () => {
    fetch('https://fakestoreapi.com/products')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Lista de productos:', json); 
      })
      .catch((error) => {
        console.error('Error al obtener productos:', error); 
      });
  };
  


  export const postChuckNorrisJoke = () => {
    const config = {
      method: 'POST',
      body: JSON.stringify({
        category: 'dev',
        joke: 'Siempre hay un bug que Chuck Norris no puede resolver porque nunca existió.',
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    };
  
    fetch('https://api.chucknorris.io/jokes', config)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Chiste creado:', json); 
      })
      .catch((error) => {
        console.error('Error al crear el chiste:', error); 
      });
  };
  


  export const updateProductService = () => {
    const config = {
      method: 'PUT',
      body: JSON.stringify({
        title: 'Producto actualizado',
        price: 99.99,
        description: 'Este es un producto actualizado desde la aplicación.',
        image: 'https://via.placeholder.com/150',
        category: 'updated-category',
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    };
  
    fetch('https://fakestoreapi.com/products/1', config)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error en la solicitud: ${response.status}`);
        }
        return response.json(); 
      })
      .then((json) => {
        console.log('Producto actualizado:', json); 
      })
      .catch((error) => {
        console.error('Error al actualizar el producto:', error); 
      });
  };
  
// este es del reto 2 con doble ee
export const createPostServicee = async (post, fnExito) => {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title: post.title,
                body: post.body,
                userId: 1,
            }),
        });

        if (!response.ok) {
            throw new Error("Error en la creación del post");
        }

        const json = await response.json();
        console.log(json);
        fnExito(); // Ejecuta la función de éxito
    } catch (error) {
        console.error("Error al crear el post:", error);
        throw error; // Lanza el error para manejo externo si es necesario
    }
};


export const getDocumentTypes = async () => {
    try {
        const response = await fetch('http://192.168.100.79/inventarios3/rest/tiposdocumento/recuperar', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Error al obtener los tipos de documentos');
        }

        const data = await response.json();
        console.log('Tipos de documentos:', data);
        return data;
    } catch (error) {
        console.error('Error en getDocumentTypes:', error);
        throw error;
    }
};


export const createDocumentTypeService = async (documentType, fnExito) => {
    try {
        const response = await fetch('http://192.168.100.79/inventarios3/rest/tiposdocumento/insertar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre: documentType.name,  
            }),
        });

        if (!response.ok) {
            throw new Error("Error al crear el tipo de documento");
        }

        const json = await response.json();
        console.log(json);
        fnExito(); 
    } catch (error) {
        console.error("Error al crear el tipo de documento:", error);
        throw error; 
    }
};


