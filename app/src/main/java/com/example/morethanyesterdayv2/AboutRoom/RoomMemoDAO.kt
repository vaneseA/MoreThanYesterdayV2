package com.example.morethanyesterdayv2.AboutRoom

import androidx.room.*

@Dao
interface RoomMemoDAO {
@Query("select * from room_memo")
    fun getAll(): List<RoomMemo>

    //인서트로 들어온 메모 넘버에 값이 있는데, 읽는값이 충돌되면 업데이트해줌
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}