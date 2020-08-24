package dev.fespinosa.linkshortener.service;

import dev.fespinosa.linkshortener.db.LinkMapper;
import dev.fespinosa.linkshortener.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LinkShortenerService {

    private Base62ConverterService base62ConverterService;
    private LinkMapper linkMapper;
    private Environment environment;

    @Autowired
    public LinkShortenerService(Base62ConverterService base62ConverterService, LinkMapper linkMapper, Environment environment) {
        this.base62ConverterService = base62ConverterService;
        this.linkMapper = linkMapper;
        this.environment = environment;
    }

    public void shorten(Link link) throws LinkAlreadyShortenedException {
        Optional<Link> linkFoundOpt = linkMapper.getLinkByUrl(link.getUrl());
        if (linkFoundOpt.isPresent()) {
            throw new LinkAlreadyShortenedException();
        }
        linkMapper.insert(link);
        String base62 = base62ConverterService.convert(link.getId());
        link.setShortUrl(buildShortUrl(base62));
        linkMapper.updateLink(link);
    }

    public String getLongLink(String code) {
        int linkId = base62ConverterService.toBase10(code);
        Optional<Link> linkOpt = linkMapper.getLink(linkId);
        return linkOpt.map(Link::getUrl).orElse("");
    }

    public List<String> getShortLinks() {
        return linkMapper.getAllLinks()
                .stream()
                .map(Link::getShortUrl)
                .collect(Collectors.toList());
    }

    private String buildShortUrl(String code) {
        String serverName = InetAddress.getLoopbackAddress().getHostName();
        String serverPort = environment.getProperty("local.server.port");
        return "http://" + serverName + ":" + serverPort + "/0/" + code;
    }


}
