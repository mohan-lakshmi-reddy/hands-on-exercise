import React, { Component } from "react";
import Post from "./Post";
class Posts extends Component {
    constructor(props) {
        super(props);

        this.state = {
            posts: []
        };
    }
    loadPosts() {
        fetch("https://jsonplaceholder.typicode.com/posts")
            .then(response => response.json())
            .then(data => {

                const postList = data.map(
                    item => new Post(item.id, item.title, item.body)
                );

                this.setState({
                    posts: postList
                });

            })
            .catch(error => {
                throw error;
            });

    }
    componentDidMount() {
        this.loadPosts();
    }
    componentDidCatch(error) {
        alert("Error : " + error.message);
    }
    render() {
        return (
            <div>
                <h1 align="center">Blog Posts</h1>
                {
                    this.state.posts.map(post => (
                        <div key={post.id}>
                            <h3>{post.title}</h3>
                            <p>{post.body}</p>
                            <hr />
                        </div>
                    ))
                }
            </div>
        );
    }
}
export default Posts;