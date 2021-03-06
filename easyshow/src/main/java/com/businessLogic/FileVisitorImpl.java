package com.businessLogic;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.businessLogic.ShowResource.EpisodeId;

public class FileVisitorImpl implements FileVisitor<Path> {
	
	private List<String> listFilesNames = new ArrayList<>();
	private Set<EpisodeId> episodesSet = new HashSet<>();
	private String glob = "glob:**/*.{mkv,avi,mp4}";
	private final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
		
	public List<String> getListFilesNames() {
		return listFilesNames;
	}
	
	public Set<EpisodeId> getEpisodesSet() {
		return episodesSet;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if(pathMatcher.matches(file)) {
			listFilesNames.add(file.getFileName().toString());
			
			String episodeName = file.getFileName().toString();
			int firstSeparator = episodeName.indexOf('[');
			int lastSeparator = episodeName.indexOf(']');
			
			// Extraction du numéro de saison et d'épisode.
			if(firstSeparator != -1 && lastSeparator != -1) {
				String couple = episodeName.substring(firstSeparator + 1, lastSeparator);
				int ep = Integer.parseInt(couple.substring(1, 3));
				int season = Integer.parseInt(couple.substring(4, couple.length()));
				
				episodesSet.add(new EpisodeId(season, ep));
			}
		}
			
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
}
