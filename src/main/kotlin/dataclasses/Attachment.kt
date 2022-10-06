package dataclasses

interface Attachment {
    var type: String
}

class Audio {
    val id: Int = 0
    val albumId: Int = 0
    val ownerId: Int = 0
    val userId: Int = 0
}

class AudioAttachment (
    override var type: String,
    val file: Audio
): Attachment

class Video {
    val id: Int = 0
    val albumId: Int = 0
    val ownerId: Int = 0
    val userId: Int = 0
}

class VideoAttachment (
    override var type: String,
    val file: Video
): Attachment

class Photo {
    val id: Int = 0
    val albumId: Int = 0
    val ownerId: Int = 0
    val userId: Int = 0
}

class PhotoAttachment(
    override var type: String,
    val file: Photo
): Attachment

class File {
    val id: Int = 0
    val albumId: Int = 0
    val ownerId: Int = 0
    val userId: Int = 0
}

class FileAttachment(
    override var type: String,
    val file: File
): Attachment

class Story {
    val id: Int = 0
    val albumId: Int = 0
    val ownerId: Int = 0
    val userId: Int = 0
}

class StoryAttachment(
    override var type: String,
    val file: Story
): Attachment
