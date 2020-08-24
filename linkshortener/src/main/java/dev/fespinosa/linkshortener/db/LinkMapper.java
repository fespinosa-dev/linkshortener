package dev.fespinosa.linkshortener.db;

import dev.fespinosa.linkshortener.model.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LinkMapper {

    @Select("SELECT * FROM link WHERE id = #{id}")
    Optional<Link> getLink(int id);

    @Select("SELECT * FROM link WHERE url = #{url}")
    Optional<Link> getLinkByUrl(String url);

    @Insert("INSERT INTO link (url) VALUES (#{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Link link);

    @Select("SELECT * FROM link")
    List<Link> getAllLinks();

    @Update("UPDATE link SET short_url = #{shortUrl} WHERE id = #{id}")
    void updateLink(Link link);
}
