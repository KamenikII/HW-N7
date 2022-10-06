import dataclasses.Comment
import dataclasses.Post
import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun add() {
        // создаём целевой сервис
        val service = WallService
        val post = Post(fromId = 0, date = 0, postType = "post", content = "test", original = null)
        // заполняем пост
        service.add(post)
        val result = (service.giveId(0) != 0)
        assertEquals(true, result)
        //assertTrue(result)
    }

    @Test
    fun updateTrue() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test1", original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test2", original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test3", original = null))
        // создаём информацию об обновлении
        val update = Post(id = 1, fromId = 0, date = 0, postType = "post", content = "new", original = null)

        // выполняем целевое действие
        val result = service.update(update)
        assertTrue(result)
    }

    @Test
    fun updateFalse1() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test1", original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test2", original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test3", original = null))
        // создаём информацию об обновлении
        val update = Post(id = 4, fromId = 0, date = 0, postType = "post", content = "new", original = null)

        // выполняем целевое действие
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun updateFalse2() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test1", canEdit = false, original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test2", original = null))
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test3", original = null))
        // создаём информацию об обновлении
        val update = Post(id = 0, fromId = 0, date = 0, postType = "post", content = "new", original = null)

        // выполняем целевое действие
        val result = service.update(update)
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService
        service.add(Post(fromId = 0, date = 0, postType = "post", content = "test1", canEdit = false, original = null))
        val comment = Comment(fromId = 0, date = 0, content = "test comment")

        service.addComment(100, comment)
    }

    @Test
    fun shouldNotThrow() {
        val newService = WallService
        newService.add(Post(fromId = 0, date = 0, postType = "post", content = "test1", canEdit = false, original = null))
        val comment = Comment(fromId = 0, date = 0, content = "test comment")

        newService.addComment(12, comment)
        val result = (newService.givePost(0).comments.giveComment(0).content.equals("test comment"))
        assertTrue(result)
    }
}