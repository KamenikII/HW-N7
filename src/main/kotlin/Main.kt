import dataclasses.Attachment
import dataclasses.Comment
import dataclasses.Comments
import dataclasses.Post

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        val i: Int = posts.size
        posts += post.copy(id = i+1)
        return posts.last()
    }

    fun addComment(id: Int, content: Comment) {
            for ((index, post) in posts.withIndex()) {
                if (post.id == id) {
                    val comm: Comments = post.comments
                    comm.addComment(content)
                    posts[index] = post.copy(comments = comm)
                    break
                }

                if (id > posts.size) throw PostNotFoundException("No post with ID $id")
            }
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes+1)
                break
            }
        }
    }

    fun update(post: Post): Boolean {
        for ((index, p) in posts.withIndex()) {
            if ((post.id == p.id) && (post.canEdit == p.canEdit)) {
                    posts[index] = p.copy(postType = post.postType, canPin = post.canPin, markedAsAds = post.markedAsAds,
                                          friendsOnly = post.friendsOnly, isPinned = post.isPinned, content = post.content)
                    return true
            }
        }
        return false
    }

    fun repost(index: Int): Post {
        val post = posts[index]
        val origin = if (post.original == null) post else post.original
        return origin
    }

    fun giveId(index: Int): Int {
        return(posts[index].id)
    }

    fun givePost(index: Int): Post {
        return posts[index]
    }
}

class PostNotFoundException(message: String) : RuntimeException(message)

class Main {
}
